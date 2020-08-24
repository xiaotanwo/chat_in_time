package com.foxandgrapes.dao;

import com.foxandgrapes.domain.User;

public interface UserDao {
    int addUser(User user);
    User queryUser(User user);
    User queryUserByName(String name);
}
