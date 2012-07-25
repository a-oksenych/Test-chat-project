package com.epam.chat;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

public interface IProducerFactory {

    ProducerTemplate createProducer(String room, CamelContext context);

}
