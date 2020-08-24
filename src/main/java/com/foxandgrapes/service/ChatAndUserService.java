package com.foxandgrapes.service;

import com.foxandgrapes.domain.ChatAndUser;

import java.util.List;

public interface ChatAndUserService {
    /**
     * 查询比现在nowChatId要大的聊天记录（带上user的信息）
     * @param nowChatId
     * @return 聊天记录的List集合
     */
    List<ChatAndUser> queryChatAndUser(Integer nowChatId);
}
