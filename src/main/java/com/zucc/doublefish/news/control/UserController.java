package com.zucc.doublefish.news.control;

import com.zucc.doublefish.news.pojo.User;
import com.zucc.doublefish.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("username");
        String pwd   = request.getParameter("pwd");

        User checkUser = userService.findUserByUname(uname);
        if(!pwd.equals(checkUser.getPwd())){
            request.getRequestDispatcher("/user/login").forward(request,response);
        }
        else{
            Cookie cookie = new Cookie("uname",uname);
            Cookie typeCookie = new Cookie("level",String.valueOf(checkUser.getLevel()));
            Cookie idCookie   = new Cookie("uid",String.valueOf(checkUser.getUid()));
            response.addCookie(cookie);
            response.addCookie(typeCookie);
            response.addCookie(idCookie);
            request.getRequestDispatcher("/user").forward(request,response);
        }
    }

    @RequestMapping("/register")
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
