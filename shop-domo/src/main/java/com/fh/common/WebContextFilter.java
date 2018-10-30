package com.fh.common;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "WebContextFilter")
public class WebContextFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            //将过滤器中的request和response跟ThreadLocal当前线程进行绑定

            System.out.println("===========filter===与当前线程进行绑定");
            WebContext.set((HttpServletRequest) req,(HttpServletResponse) resp);
            //继续执行访问下一个目标资源
            chain.doFilter(req, resp);
        } finally {
            //最后移除绑定的线程
            System.out.println("============filter=====移除绑定");
            WebContext.remove();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
