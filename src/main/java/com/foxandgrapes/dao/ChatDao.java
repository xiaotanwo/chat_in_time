package com.foxandgrapes.dao;

import com.foxandgrapes.domain.Chat;

public interface ChatDao {
    int addChat(Chat chat);
    Chat queryLastChat();
}
