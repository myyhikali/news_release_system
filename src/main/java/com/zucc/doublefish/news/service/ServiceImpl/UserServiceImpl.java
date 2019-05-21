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

    public boolean registerUser(String uname, String pwd, int level) {
        User user = findUserByUname(uname);
        if(user==null){
            User newUser = new User();
            newUser.setLevel(level);
            newUser.setUname(uname);
            newUser.setPwd(pwd);
            userDao.insertUser(newUser);
            return true;
        }
        else {
            return false;
        }
    }

}
