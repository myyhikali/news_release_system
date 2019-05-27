package com.zucc.doublefish.news.interceptor;

import com.zucc.doublefish.news.listener.SessionListener;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LoginInterceptor  implements HandlerInterceptor {
    public boolean preHandle(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Object o) throws Exception {
        
        for(Cookie cookie:httpServletRequest.getCookies()){
            if(cookie.getName().equals("SESSIONID")){
                HttpSession session = SessionListener.sessionMap.get(cookie.getValue());
                if(session.getAttribute("uname")==null){
                    return false;
                }
            }
        }
        return true;
    }

    public void postHandle(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
