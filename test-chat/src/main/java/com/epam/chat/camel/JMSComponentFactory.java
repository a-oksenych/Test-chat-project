package com.epam.chat.camel;

import com.epam.chat.IConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.Component;
import org.apache.camel.component.jms.JmsComponent;

import javax.jms.ConnectionFactory;

public class JMSComponentFactory implements IConnectionFactory {

    @Override
    public Component createComponent(String url) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Component result = JmsComponent.jmsComponent(connectionFactory);
        return result;
    }

}
