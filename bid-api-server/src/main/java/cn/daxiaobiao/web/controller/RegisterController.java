package cn.daxiaobiao.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cheng on 2015/11/1.
 */
@Controller
@RequestMapping("")
public class RegisterController {

    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @RequestMapping(value="/register", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response){
        return new ModelAndView("/Page/register",null);
    }
}