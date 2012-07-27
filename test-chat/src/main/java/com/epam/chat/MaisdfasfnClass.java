package com.epam.chat;

import com.epam.chat.service.LocalMessageCache;
import com.epam.chat.service.MessageService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author Maksym_Labazov
 * 
 */
public class MaisdfasfnClass {

    /**
     * @param args s
     * @throws InterruptedException s
     */
    public void main2222(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("chat-context.xml");
        LocalMessageCache messages = context.getBean(LocalMessageCache.class);

        MessageService messageService = context.getBean(MessageService.class);
        String room = "topic:test-room";
        messageService.addRoom(room);

        messageService.writeMessage(room, "Hello world!!!");

        // Thread.currentThread().sleep(1000);
        List<String> messagesFromRoom = messages.getMessagesFromRoom(room);

        // System.out.println(messagesFromRoom);
    }

}
