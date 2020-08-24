package com.foxandgrapes.service.impl;

import com.foxandgrapes.dao.ChatAndUserDao;
import com.foxandgrapes.domain.ChatAndUser;
import com.foxandgrapes.service.ChatAndUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChatAndUserServiceImpl implements ChatAndUserService {
    @Resource
    private ChatAndUserDao chatAndUserDao;
    @Override
    public List<ChatAndUser> queryChatAndUser(Integer nowChatId) {
        return chatAndUserDao.queryChatJoinUser(nowChatId);
    }
}
