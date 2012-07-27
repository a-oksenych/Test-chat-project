package com.epam.chat.camel;

import com.epam.chat.IRouteFactory;
import com.epam.chat.helper.MessageBuilder;

import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Maksym_Labazov
 * 
 */
public class ActivemqRouteFactory implements IRouteFactory, ApplicationContextAware {

    private Logger log = Logger.getLogger(this.getClass());
    private ApplicationContext applicationContext;

    @Override
    public void createRoute(final String componentName, final String room, final String clientId,
            final CamelContext context) {
        RoutesBuilder builder = new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                Object beanType = getMessageListner(room);
                // TODO make it better
                String fromUri = MessageBuilder.buildRouteForConsumer(room, componentName, clientId);
                from(fromUri).bean(beanType);
            }
        };
        try {
            context.addRoutes(builder);
        } catch (Exception e) {
            log.error("Can't add route to Camel context.");
        }
    }

    private MessageListener getMessageListner(String room) {
        MessageListener messageListener = applicationContext.getBean(MessageListener.class);
        messageListener.setRoom(room);
        return messageListener;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
