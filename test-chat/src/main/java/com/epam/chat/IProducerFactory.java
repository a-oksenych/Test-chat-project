package com.epam.chat;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

/**
 * @author Maksym_Labazov
 * 
 */
public interface IProducerFactory {

    /**
     * @param room s
     * @param context s
     * @return s
     */
    ProducerTemplate createProducer(String room, CamelContext context);

}
