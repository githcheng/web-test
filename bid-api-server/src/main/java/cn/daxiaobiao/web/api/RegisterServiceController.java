package cn.daxiaobiao.web.api;

import cn.daxiaobiao.core.dao.UserDao;
import cn.daxiaobiao.core.model.User;
import cn.daxiaobiao.redis.IRedisService;
import cn.daxiaobiao.web.util.CommonUtil;
import cn.daxiaobiao.web.util.ConstVar;
import cn.daxiaobiao.web.util.JacksonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("register")
public class RegisterServiceController {

    private final Logger logger = LoggerFactory.getLogger(RegisterServiceController.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private IRedisService redisService;

    @RequestMapping(value="/registerService", method={RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    JsonNode registerService(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("phone") String phone,
            String username,
            @RequestParam("password") String password,
            @RequestParam("authPictureCode") String authPictureCode,
            @RequestParam("authSmsCode") String authSmsCode,
            String company
    ){


        logger.info("phone:{}, username:{}, password:{}," +
                "authPictureCode:{}, authSmsCode:{}",phone, username, password,
                authPictureCode,authSmsCode);

        // 验证参数合法性
        checkArgument(phone != null, "电话号码是空值");
        checkArgument(password != null, "密码是空值");
        checkArgument(authPictureCode != null, "图片验证码是空值");
        checkArgument(authSmsCode != null, "短信验证码是空值");

        boolean isPhone = CommonUtil.isPhone(phone);
        if (!isPhone){
            return JacksonUtil.fail("手机号码格式不正确");
        }

        if (username == null){
            username = phone;
        }

        // 验证短信码
        String smsCode = redisService.get(ConstVar.smsPrefix + phone);
        if (smsCode==null || !smsCode.equals(authSmsCode)){
            // 验证不通过
            return JacksonUtil.fail("短信验证码错误");
        }

        // 验证图片码
        String pictureCode = redisService.get(ConstVar.picturePrefix + phone);
        if (pictureCode==null || !pictureCode.equals(authPictureCode)){
            // 验证不通过
            return JacksonUtil.fail("图片验证码错误");
        }

        User exist = userDao.getUserByPhone(phone);
        if (exist != null){
            // 有手机号重复的
            return JacksonUtil.fail("您的手机号码已经被注册过了");
        }

        User user = new User(username, username, password, "", phone, "");
        int status = userDao.insert(user);
        logger.info("insert status:{}", status);

        ObjectNode objectNode = JacksonUtil.getObjectNode();
        objectNode.put("code",0);
        objectNode.put("msg", "success");
        return JacksonUtil.ok();
    }
}