package com.foxandgrapes.service;

import com.foxandgrapes.domain.Chat;

public interface ChatService {
    /**
     * 添加聊天记录
     * @param chat
     * @return true：添加成功，false：添加失败
     */
    boolean addChat(Chat chat);

    /**
     * 查询最后一条聊天记录的id
     * @return 最后一条聊天记录的id
     */
    Integer queryLastChatId();
}
