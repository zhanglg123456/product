package com.fh.util;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获得 uri 的请求
        String uri = request.getRequestURI();
        String localAddr = request.getLocalAddr();

        Object object = request.getSession().getAttribute("loginuser");

        if ( object!=null  || uri.equals("/login")) {
            return true;
        }else{

            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return false;
        }
    }
}
