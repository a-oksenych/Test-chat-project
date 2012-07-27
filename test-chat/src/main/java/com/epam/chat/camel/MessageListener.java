package com.epam.chat.camel;

import com.epam.chat.IMessageCache;

/**
 * @author Maksym_Labazov
 * 
 */
public class MessageListener {

    private IMessageCache messageCache;
    private String room;

    /**
     * @return s
     */
    public final String getRoom() {
        return room;
    }

    /**
     * @param room s
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * @param message s
     */
    public void listen(String message) {
        messageCache.addMessage(room, message);
        // System.out.println(message);
    }

    /**
     * @return s
     */
    public final IMessageCache getMessageCache() {
        return messageCache;
    }

    /**
     * @param messageCache s
     */
    public final void setMessageCache(IMessageCache messageCache) {
        this.messageCache = messageCache;
    }

}
