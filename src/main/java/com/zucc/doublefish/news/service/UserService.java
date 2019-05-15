package com.zucc.doublefish.news.service;

import com.zucc.doublefish.news.pojo.User;

public interface UserService {
    public User findUserByUname(String uname);
    public boolean registerUser(String uname,String pwd,int level);
}
