package com.fh.api.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebContext {

    private static  ThreadLocal<HttpServletRequest>   requestThreadLocal = new ThreadLocal<>();

    private  static  ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal<>();



    public static  void  set(HttpServletRequest request,HttpServletResponse response){

        requestThreadLocal.set(request);
        responseThreadLocal.set(response);

    }

    public static  HttpServletRequest getRequest(){

        HttpServletRequest request = requestThreadLocal.get();
        return request;

    }

    public static  HttpServletResponse  getResponse(){

        HttpServletResponse response = responseThreadLocal.get();


        return response;
    }


    public static  void  remove(){

        requestThreadLocal.remove();
        responseThreadLocal.remove();
    }
}
