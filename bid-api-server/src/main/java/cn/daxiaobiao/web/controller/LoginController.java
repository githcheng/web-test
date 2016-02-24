package cn.daxiaobiao.web.controller;

import cn.daxiaobiao.core.dao.UserDao;
import cn.daxiaobiao.core.model.User;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserDao userDao;

    @RequestMapping(value="/login", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("username") String username,
            @RequestParam("password") String password){

//        String username = request.getParameter("username");
//        String password = request.getParameter("password");

        logger.info("username:{}, password:{}", username,password);

        User user = userDao.getUser(username);
        if (!password.equals(user.getPassword())){
            // 密码不正确
            return new ModelAndView("/Page/login",null);
        }
        request.getSession().setAttribute("user", user);//登录成功，将用户数据放入到Session中
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //进行重定向，并且下面的代码不再执行
        return new ModelAndView("/index",null);
    }
}
