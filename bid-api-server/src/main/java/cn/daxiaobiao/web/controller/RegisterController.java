package cn.daxiaobiao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("")
public class RegisterController {
    @RequestMapping("/register")
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response){

//        String username = request.getParameter("phone");
//        String password = request.getParameter("password");
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");

        return new ModelAndView("/Page/register",null);
    }
}
