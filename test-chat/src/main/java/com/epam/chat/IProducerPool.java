package com.epam.chat;

import org.apache.camel.ProducerTemplate;

public interface IProducerPool {

    void addProducer(String room, ProducerTemplate producer);

    ProducerTemplate getProducer(String room);

    void removeProducer(String room);

}
