package com.epam.chat;

import org.apache.camel.ProducerTemplate;

/**
 * @author Maksym_Labazov
 * 
 */
public interface IProducerPool {

    /**
     * @param room s
     * @param producer s
     */
    void addProducer(String room, ProducerTemplate producer);

    /**
     * @param room s
     * @return s
     */
    ProducerTemplate getProducer(String room);

    /**
     * @param room s
     */
    void removeProducer(String room);

}
