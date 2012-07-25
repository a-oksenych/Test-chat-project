package com.epam.chat.camel;

import com.epam.chat.IMessageCache;

public class MessageListener {

    private IMessageCache messageCache;
    private String room;

    public final String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void listen(String message) {
        messageCache.addMessage(room, message);
        System.out.println(message);
    }

    public final IMessageCache getMessageCache() {
        return messageCache;
    }

    public final void setMessageCache(IMessageCache messageCache) {
        this.messageCache = messageCache;
    }

}
