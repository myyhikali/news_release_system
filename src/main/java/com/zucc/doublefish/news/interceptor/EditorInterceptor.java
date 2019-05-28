package com.zucc.doublefish.news.interceptor;

import com.zucc.doublefish.news.listener.SessionListener;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditorInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(httpServletRequest.getMethod().equals("OPTIONS"))
        {
            return true;
        }
        int level= -1;

        for(Cookie cookie:httpServletRequest.getCookies()){
            if(cookie.getName().equals("SESSIONID")){
                level = (Integer) SessionListener.sessionMap.get(cookie.getValue()).getAttribute("level");
                break;
            }
        }
        if(level==0||level==-1)
        {
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
