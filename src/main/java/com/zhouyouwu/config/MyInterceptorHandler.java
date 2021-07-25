package com.zhouyouwu.config;

import com.zhouyouwu.utils.JwtUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Administrator
 */
@Log4j
@Component
public class MyInterceptorHandler implements HandlerInterceptor {
    private String[] Urls;

    public String[] getUrls() {
        return Urls;
    }

    public void setUrls(String[] Urls) {
        this.Urls = Urls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("开始拦截.....................");

        boolean flag = true;
        String uri = request.getRequestURI();
        for(String exceptUrl: Urls){
            if (uri.startsWith(exceptUrl)) {
                flag = false;
                break;
            }
        }

        if(flag){
            return true;
        }

        String token = request.getHeader("token");
        try{
            JwtUtil.parseJWT(token);
            return true;
        }catch (Exception e){
            request.setAttribute("msg", e.getMessage());
        }

        log.info("被拦截"+uri);
        //重置response
        response.reset();
        //设置编码格式
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter pw = response.getWriter();
        pw.write("{\"code\":\"1005\",\"message\":\"没有登录\"}");
        pw.flush();
        pw.close();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println(request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
