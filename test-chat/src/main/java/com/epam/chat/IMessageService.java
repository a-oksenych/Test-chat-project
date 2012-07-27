package com.epam.chat;

/**
 * @author Maksym_Labazov
 * 
 */
public interface IMessageService {

    /**
     * @param room s
     */
    void addRoom(String room);

    /**
     * @param room s
     */
    void leaveRoom(String room);

    /**
     * @param room s
     * @param message s
     */
    void writeMessage(String room, String message);

}
