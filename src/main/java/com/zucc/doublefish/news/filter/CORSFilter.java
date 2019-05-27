package com.zucc.doublefish.news.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CROSFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpServletRequest  request  = (HttpServletRequest)servletRequest;

        System.out.println("filter");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, If-Modified-Since,REDIRECT,CONTEXTPATH");
        response.setHeader("Access-Control-Expose-Headers", "REDIRECT,CONTEXTPATH");

        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

//        for(Cookie cookie:request.getCookies()){
//            if(cookie.getName().equals("editor")){
//                filterChain.doFilter(servletRequest,servletResponse);
//            }
//        }
        filterChain.doFilter(request,response);
        //request.getRequestDispatcher("/").forward(request,response);
    }

    public void destroy() {

    }
}
