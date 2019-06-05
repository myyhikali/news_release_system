package com.zucc.doublefish.news.control;

import com.zucc.doublefish.news.listener.SessionListener;
import com.zucc.doublefish.news.pojo.Result;
import com.zucc.doublefish.news.pojo.User;
import com.zucc.doublefish.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/testCookies")
    public void testCookies(HttpSession session, HttpServletRequest request, HttpServletResponse response){

        response.setCharacterEncoding("UTF-8");
        System.out.println(request.getCookies().length);
        Cookie cookie = new Cookie("username","admin");
        response.addCookie(cookie);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result rs = new Result();
        Object sessionId = null;
        Object level =null;
        for(Cookie cookie:request.getCookies()){
            if(cookie.getName().equals("SESSIONID")){
                sessionId = cookie.getValue();
                cookie.setValue(null);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        if(sessionId!=null)
        {
            Cookie level_cookie = new Cookie("level",null);
            level_cookie.setMaxAge(0);
            level_cookie.setPath("/");
            response.addCookie(level_cookie);
            rs.setStatus("succeed");
            SessionListener.sessionMap.get(sessionId).removeAttribute("uid");
            SessionListener.sessionMap.get(sessionId).removeAttribute("uname");
            SessionListener.sessionMap.get(sessionId).removeAttribute("level");
        }
        else{
            rs.setStatus("failed");
        }

        return rs;
    }

    @RequestMapping("/username")
    @ResponseBody
    public Result username(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result rs = new Result();
        String uname = null;
        for(Cookie cookie:request.getCookies()){
            if(cookie.getName().equals("SESSIONID")){

                uname = (String)request.getSession().getAttribute("uname");
            }
        }
        if(uname!=null)
        {
            rs.setStatus("succeed");
            rs.setContent(uname);
        }
        else
            rs.setStatus("failed");

        return rs;
    }
    @RequestMapping("/checklogin")
    @ResponseBody
    public Result login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String uname = request.getParameter("username");
        String pwd   = request.getParameter("pwd");
        Result rs = new Result();

        User checkUser = userService.findUserByUname(uname);
        if(checkUser==null||!pwd.equals(checkUser.getPwd())){
            response.setHeader("REDIRECT","REDIRECT");
            response.setHeader("CONTEXTPATH","login.html");
            rs.setStatus("failed");
        }
        else{

            Cookie cookie = new Cookie("SESSIONID",request.getSession().getId());
            request.getSession().setAttribute("uname", uname);
            request.getSession().setAttribute("uid", checkUser.getUid());
            request.getSession().setAttribute("level", checkUser.getLevel());
            Cookie typeCookie = new Cookie("level",String.valueOf(checkUser.getLevel()));
            cookie.setPath("/");
            typeCookie.setPath("/");
            response.addCookie(cookie);
            response.addCookie(typeCookie);

            response.setHeader("REDIRECT","REDIRECT");
            response.setHeader("CONTEXTPATH","index.html");
            rs.setStatus("succeed");
        }

        return rs;
    }

    @RequestMapping("/register")
    @ResponseBody
    public Result register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("lusername");
        String pwd   = request.getParameter("lpwd");
        String pwd1   = request.getParameter("lpwd1");
        Result rs = new Result();
        if(uname==null||!pwd.equals(pwd1)){
            response.setHeader("REDIRECT","REDIRECT");
            response.setHeader("CONTEXTPATH","register.html");
            rs.setStatus("username is null or pwd not equal pwd1");
            return rs;
        }
        User checkUser = userService.findUserByUname(uname);
        if(checkUser!=null){
            response.setHeader("REDIRECT","REDIRECT");
            response.setHeader("CONTEXTPATH","register.html");
            rs.setStatus("username is exist");
        }
        else{
            int level;
            if (request.getParameter("choice").equals("editor")){
                level=0;
            }
            else
                level=1;
            userService.registerUser(uname,pwd,level);
            response.setHeader("REDIRECT","REDIRECT");
            response.setHeader("CONTEXTPATH","login.html");
            rs.setStatus("succeed");
        }

        return rs;
    }
}
