package com.zhouyouwu.config;

import com.zhouyouwu.utils.JwtUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
@Log4j
@Component
public class MyInterceptorHandler implements HandlerInterceptor {
    private String[] exceptUrls;

    public String[] getExceptUrls() {
        return exceptUrls;
    }

    public void setExceptUrls(String[] exceptUrls) {
        this.exceptUrls = exceptUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("开始拦截.....................");
//
//        String uri = request.getRequestURI();
//        for(String exceptUrl: exceptUrls){
//            if(uri.startsWith(exceptUrl)){
//                return true;
//            }
//        }
//
//        String token = request.getHeader("token");
//        try{
//            JwtUtil.parseJWT(token);
//            return true;
//        }catch (Exception e){
//            request.setAttribute("msg", e.getMessage());
//        }
//
//        System.out.println("被拦截"+uri);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println(request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
