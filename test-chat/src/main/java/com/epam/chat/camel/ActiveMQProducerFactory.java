package com.epam.chat.camel;

import com.epam.chat.IProducerFactory;
import com.epam.chat.helper.MessageBuilder;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.log4j.Logger;

public class ActiveMQProducerFactory implements IProducerFactory {

    private Logger logger = Logger.getLogger(ActiveMQProducerFactory.class);

    @Override
    public ProducerTemplate createProducer(String room, CamelContext context) {
        ProducerTemplate result = context.createProducerTemplate();
        result.setDefaultEndpointUri(MessageBuilder.buildRoute(room));
        try {
            result.start();
        } catch (Exception e) {
            // TODO think about better processing of start exception
            logger.error("Can't start room producer.");
        }
        return result;
    }

}
