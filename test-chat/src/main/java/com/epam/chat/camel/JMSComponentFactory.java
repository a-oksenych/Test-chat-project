package com.epam.chat.camel;

import com.epam.chat.IConnectionFactory;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.Component;

public class JMSComponentFactory implements IConnectionFactory {

    @Override
    public Component createComponent(String url) {
        Component result = ActiveMQComponent.activeMQComponent(url);
        return result;
    }

}
