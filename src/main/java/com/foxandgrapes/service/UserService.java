package com.foxandgrapes.service;

import com.foxandgrapes.domain.User;

public interface UserService {
    /**
     * 查询是否已存在该用户（注册时）
     * @param name
     * @return true:存在，false:不存在
     */
    Boolean existence(String name);

    /**
     * 查询用户名和密码是否一致(存在)（登陆时）
     * @param user
     * @return true:一致（存在），false:不一致（不存在）
     */
    Boolean existence(User user);

    /**
     * 注册该用户
     * @param user
     * @return true:注册成功，false:注册失败
     */
    Boolean addUser(User user);

    /**
     * 获取该用户的id
     * @param user
     * @return id的字符串
     */
    Integer getUserId(User user);
}
