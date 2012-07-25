package com.epam.chat;

import java.util.List;

public interface IMessageCache {

    List<String> getMessagesFromRoom(String room);
    void addMessage(String room, String message);
    
}
