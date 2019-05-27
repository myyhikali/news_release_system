package com.zucc.doublefish.news.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;

public class SessionListener implements HttpSessionListener {
    public static Map<String,HttpSession> sessionMap;
    public void sessionCreated(HttpSessionEvent se) {
        sessionMap.put(se.getSession().getId(), se.getSession());
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        sessionMap.remove(se.getSession().getId());
    }
}
