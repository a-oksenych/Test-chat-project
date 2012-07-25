import com.epam.chat.service.LocalMessageCache;
import com.epam.chat.service.MessageService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("chat-context.xml");
        LocalMessageCache messages = context.getBean(LocalMessageCache.class);

        MessageService messageService = context.getBean(MessageService.class);
        String room = "topic:test-room";
        messageService.addRoom(room);

        messageService.writeMessage(room, "Hello world!!!");

        Thread.currentThread().sleep(1000);
        List<String> messagesFromRoom = messages.getMessagesFromRoom(room);

        System.out.println(messagesFromRoom);
    }

}
