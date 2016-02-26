package cn.daxiaobiao.web.api;

import cn.daxiaobiao.core.dao.UserDao;
import cn.daxiaobiao.core.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("")
public class LoginServiceController {

    private final Logger logger = LoggerFactory.getLogger(LoginServiceController.class);

    @Autowired
    private UserDao userDao;

    @RequestMapping(value="/loginIn", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response, @RequestParam("phone") String phone,
            @RequestParam("password") String password){

        logger.info("username:{}, password:{}", phone,password);

        RedirectView view = null;
        User user = userDao.getUserByPhone(phone);
        if (password == null || user== null || !password.equals(user.getPassword())){
            // 密码不正确
            return new ModelAndView(new RedirectView("/login"));
        }

        HttpSession session = request.getSession();
        session.setAttribute("username", user);
        session.setMaxInactiveInterval(2 * 3600);  // Session保存两小时
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setMaxAge(2 * 3600);  // 客户端的JSESSIONID也保存两小时
        session.setMaxInactiveInterval(2 * 3600);
        cookie.setPath("/");
        response.addCookie(cookie);

        logger.info("redirct index");
        return new ModelAndView(new RedirectView("/"));
    }
}