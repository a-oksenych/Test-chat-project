package com.epam.chat;

public interface IMessageService {

    void addRoom(String room);

    void leaveRoom(String room);

    void writeMessage(String room, String message);

}
