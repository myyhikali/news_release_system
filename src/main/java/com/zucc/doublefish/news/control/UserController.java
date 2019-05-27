package com.zucc.doublefish.news.control;

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

    @RequestMapping("/checklogin")
    @ResponseBody
    public Result login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
//            Cookie idCookie   = new Cookie("uid",String.valueOf(checkUser.getUid()));
            cookie.setPath("/");
            typeCookie.setPath("/");
//            idCookie.setPath("/");
            response.addCookie(cookie);
            response.addCookie(typeCookie);
//            response.addCookie(idCookie);
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
