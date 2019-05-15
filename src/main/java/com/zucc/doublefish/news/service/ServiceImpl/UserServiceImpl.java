package com.zucc.doublefish.news.service.ServiceImpl;

import com.zucc.doublefish.news.dao.UserDao;
import com.zucc.doublefish.news.pojo.User;
import com.zucc.doublefish.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    public User findUserByUname(String uname) {
        return this.userDao.findUserByUname(uname);
    }
}
