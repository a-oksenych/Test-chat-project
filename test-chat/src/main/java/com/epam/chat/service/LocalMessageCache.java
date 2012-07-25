package com.epam.chat.service;

import com.epam.chat.IMessageCache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LocalMessageCache implements IMessageCache {

    private Map<String, List<String>> cache;

    public LocalMessageCache() {
        cache = new HashMap<>();
    }

    @Override
    public List<String> getMessagesFromRoom(String room) {
        synchronized (cache) {
            List<String> result = cache.get(room);
            return result;
        }
    }

    @Override
    public void addMessage(String room, String message) {
        synchronized (cache) {
            List<String> messageList = cache.get(room);
            if (messageList == null) {
                messageList = new LinkedList<>();
                cache.put(room, messageList);
            }
            messageList.add(message);
        }
    }

}
