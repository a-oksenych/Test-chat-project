package com.epam.chat;

import java.util.List;

/**
 * @author Maksym_Labazov
 * 
 */
public interface IMessageCache {

    /**
     * @param room s
     * @return s a
     */
    List<String> getMessagesFromRoom(String room);

    /**
     * @param room s
     * @param message s
     */
    void addMessage(String room, String message);

}
