package com.foxandgrapes.service.impl;

import com.foxandgrapes.dao.ChatDao;
import com.foxandgrapes.domain.Chat;
import com.foxandgrapes.service.ChatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ChatServiceImpl implements ChatService {
    @Resource
    private ChatDao chatDao;

    @Override
    public boolean addChat(Chat chat) {
        int cnt = chatDao.addChat(chat);
        if (cnt == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Integer queryLastChatId(){
        Chat chat = chatDao.queryLastChat();
        if (chat == null) {
            return 0;
        }
        return chat.getId();
    }
}
