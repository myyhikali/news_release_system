package com.zucc.doublefish.news.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import java.util.HashMap;
import java.util.Map;

public class SessionListener implements HttpSessionListener {
    public static Map<String, HttpSession> sessionMap = new HashMap<String, HttpSession>();

    public void sessionCreated(HttpSessionEvent httpSessionEvent){
        HttpSession session = httpSessionEvent.getSession();
        sessionMap.put(session.getId(),session);
        System.out.println(session.getId());
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent){
        HttpSession session = httpSessionEvent.getSession();
        sessionMap.remove(session.getId());
    }
}
