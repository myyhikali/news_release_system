package com.zucc.doublefish.news.dao;

import com.zucc.doublefish.news.pojo.User;

public interface UserDao {
    public User findUserByUname(String uname);

}
