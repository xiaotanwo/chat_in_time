package com.foxandgrapes.dao;

import com.foxandgrapes.domain.ChatAndUser;

import java.util.List;

public interface ChatAndUserDao {
    List<ChatAndUser> queryChatJoinUser(Integer id);
}
