package cn.daxiaobiao.web.api;

import cn.daxiaobiao.core.dao.UserDao;
import cn.daxiaobiao.core.model.User;
import cn.daxiaobiao.core.service.JavaSmsApi;
import cn.daxiaobiao.redis.IRedisService;
import cn.daxiaobiao.web.util.CommonUtil;
import cn.daxiaobiao.web.util.ConstVar;
import cn.daxiaobiao.web.util.JacksonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("login")
public class LoginServiceController {

    private final Logger logger = LoggerFactory.getLogger(LoginServiceController.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private JavaSmsApi javaSmsApi;

    @Autowired
    private IRedisService redisService;

    @RequestMapping(value="/loginIn", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    JsonNode loginIn(HttpServletRequest request,
            HttpServletResponse response, @RequestParam("phone") String phone,
            @RequestParam("password") String password,
            @RequestParam("authCode") String authCode) throws IOException {

        logger.info("username:{}, password:{}", phone,password);

        RedirectView view = null;
        User user = userDao.getUserByPhone(phone);
        if (user== null){
            // 用户不存在
            return JacksonUtil.fail("用户不存在");
        }

        if (password == null || !password.equals(user.getPassword())){
            // 密码不正确
            return JacksonUtil.fail("密码错误");
        }

        HttpSession session = request.getSession();
        session.setAttribute("username", user);
        session.setMaxInactiveInterval(2 * 3600);  // Session保存两小时
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setMaxAge(2 * 3600);  // 客户端的JSESSIONID也保存两小时
        session.setMaxInactiveInterval(2 * 3600);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect("/");

        return JacksonUtil.ok();
    }

    @RequestMapping(value="/loginOut", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    JsonNode loginOut(HttpServletRequest request,
                   HttpServletResponse response, @RequestParam("phone") String phone,
                   @RequestParam("password") String password) throws IOException {

        logger.info("username:{}, password:{}", phone,password);

        RedirectView view = null;
        User user = userDao.getUserByPhone(phone);
        if (user== null){
            // 用户不存在
            return JacksonUtil.fail("用户不存在");
        }

        if (password == null || !password.equals(user.getPassword())){
            // 密码不正确
            return JacksonUtil.fail("密码错误");
        }

        HttpSession session = request.getSession(false);//防止创建Session
        if(session != null){
            session.removeAttribute("username");
        }

        response.sendRedirect("/login");
        return JacksonUtil.ok();
    }

    @RequestMapping(value="/findPassWord", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    JsonNode findPassWord(HttpServletRequest request,
                     HttpServletResponse response, @RequestParam("phone") String phone,
                     @RequestParam("authCode") String authCode) throws IOException {

        logger.info("username:{}, authCode:{}", phone,authCode);

        User user = userDao.getUserByPhone(phone);
        if (user== null){
            // 用户不存在
            return JacksonUtil.fail("用户不存在");
        }

        String redisAuthCode = redisService.get(ConstVar.picturePrefix + phone);
        logger.info("redisAuthCode:{}, authCode:{}", redisAuthCode,authCode);
        if (redisAuthCode == null || !redisAuthCode.equals(authCode)){
            return JacksonUtil.fail("图片验证码错误");
        }

        String password = user.getPassword();
        String text = String.format("【大小标网】您的登录密码是%s，请妥善保管!", password);
        try {
            if (ConstVar.smsSwitch){
                javaSmsApi.sendSms(text,phone);
            }
        } catch (IOException e) {
            return JacksonUtil.fail("获取验证码失败,请稍后再试");
        }
        response.sendRedirect("/login");
        return JacksonUtil.ok("已经向您的手机发送密码,请登录");
    }
}