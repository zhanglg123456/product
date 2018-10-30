package com.fh.interceptor;

import com.fh.common.SystemConst;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获得 uri 的请求
        String uri = request.getRequestURI();
        String localAddr = request.getLocalAddr();

        System.out.println("执行拦截器preHandle方法============"+uri);
        Object object = request.getSession().getAttribute(SystemConst.USER_MASSAGE);

        if ( object!=null  || uri.equals("/login")) {
            return true;
        }else{

            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return false;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("执行拦截器==========postHandle方法");

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("执行拦截器==========afterCompletion方法");
    }
}
