package cn.daxiaobiao.web.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明: 登录控制
 */
public class LoginControlInterceptor implements HandlerInterceptor {

    private static String loginUrl = "/login";

    // 放行url
    public static List<String> passUrls = new ArrayList<>();

    static {
        passUrls.add("/index.html");
        passUrls.add("/login");
        passUrls.add("/Page/login");
        passUrls.add("/detail");
        passUrls.add("/digest");

        //测试
        passUrls.add("/test");

        passUrls.add("/register");
        passUrls.add("/Page/register");
        // 静态资源
        passUrls.add("/img");passUrls.add("/js");
        passUrls.add("/css");
    }

    private final Logger logger = LoggerFactory.getLogger(LoginControlInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1、请求到登录页面 放行
        logger.info("request.getServletPath() : {}",request.getServletPath());
        for (String url : passUrls){
            if (request.getServletPath().equals("/") || request.getServletPath().startsWith(url)){
                logger.info("request.getServletPath():{}, path url:{}",request.getServletPath(),url);
                return true;
            }
        }

        //2、TODO 比如退出、首页等页面无需登录，即此处要放行 允许游客的请求


        Cookie[] cookies = request.getCookies();
        if (null != cookies){
            for(Cookie cookie: cookies){
//                logger.info("cookie name : {}", cookie.getName());
                if(cookie.getName().equals("JSESSIONID")){
                    // 如果user Cookie存在，进行处理
                    if (request.getSession().getAttribute("username") != null){
                        //3、如果用户已经登录 放行
                        return true;
                    }
                }
            }
        } else {
            logger.info("cookies is null");
        }


        //4、非法请求 即这些请求需要登录后才能访问
        //重定向到登录页面
        logger.info("跳转到登录页->>>>");
        response.sendRedirect(request.getContextPath() + loginUrl);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
