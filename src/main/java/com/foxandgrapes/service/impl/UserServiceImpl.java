package com.foxandgrapes.service.impl;

import com.foxandgrapes.dao.UserDao;
import com.foxandgrapes.domain.User;
import com.foxandgrapes.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Boolean existence(String name) {
        User mysqlUser = userDao.queryUserByName(name);
        if (mysqlUser == null) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean existence(User user) {
        User mysqlUser = userDao.queryUser(user);
        if (mysqlUser == null) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean addUser(User user) {
        int cnt = userDao.addUser(user);
        if (cnt != 1) {
            return false;
        }
        return true;
    }

    public Integer getUserId(User user) {
        User mysqlUser = userDao.queryUser(user);
        if (mysqlUser != null) {
            return mysqlUser.getId();
        }
        return 0;
    }
}
