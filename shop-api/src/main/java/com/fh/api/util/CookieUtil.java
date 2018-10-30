package com.fh.api.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieUtil {

    public static void  writerCookie(HttpServletResponse response,String key, String value , String domain, int maxAge){
        //给cookie里的中文编码utf-8,解决中文乱码
        try {
            value= URLEncoder.encode(value,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
        Cookie cookie = new Cookie(key,value);

        cookie.setDomain(domain);

        cookie.setPath("/");
        //判断是否是持久化cookie,还是要设置成零删除cookie
        if(maxAge>-1){
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }


    public static void deleteCookie(HttpServletResponse response,String key,String domain){

        CookieUtil.writerCookie(response,key,null,domain,0);

    }


    public static String readerCookie(HttpServletRequest request,String key){
        Cookie[] cookies = request.getCookies();
        String value ="";
        if(null!=cookies&&cookies.length>0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(key)){
                    //取cookie中的中文时要解码
                    try {
                        value = URLDecoder.decode(cookie.getValue(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        throw  new RuntimeException(e);
                    }
                }
            }
        }
       return value;
    }
}
