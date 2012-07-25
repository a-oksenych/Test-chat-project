package com.epam.chat.service;

import com.epam.chat.IConnectionFactory;
import com.epam.chat.IMessageService;
import com.epam.chat.IProducerFactory;
import com.epam.chat.IProducerPool;
import com.epam.chat.IRouteFactory;
import com.epam.chat.IUserContext;
import com.epam.chat.helper.MessageBuilder;

import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.camel.ProducerTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

public class MessageService implements InitializingBean, IMessageService {

    Logger log = Logger.getLogger(MessageService.class);

    private IConnectionFactory connectionFactory;
    private String activeMQServerUrl;
    private IRouteFactory routeFactory;
    private IProducerFactory producerFactory;
    private CamelContext camelContext;
    private IProducerPool producerPool;
    private IUserContext userContext;

    @Override
    public void addRoom(String room) {
        String clientId = userContext.getClientId();
        routeFactory.createRoute(MessageBuilder.DEFAULT_JMS_COMPONENT_NAME, room, clientId, camelContext);
        ProducerTemplate producer = producerFactory.createProducer(room, camelContext);
        producerPool.addProducer(room, producer);
    }

    @Override
    public void leaveRoom(String room) {
        try {
            // TODO check this place
            camelContext.removeRoute(room);
            ProducerTemplate producer = producerPool.getProducer(room);
            producer.stop();
            producerPool.removeProducer(room);
        } catch (Exception e) {
            log.error("Can't remove room.");
        }
    }

    @Override
    public void writeMessage(String room, String message) {
        ProducerTemplate producer = producerPool.getProducer(room);
        producer.sendBody(message);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO think how to work with component name (not as constant)
        Component component = connectionFactory.createComponent(activeMQServerUrl);
        camelContext.addComponent(MessageBuilder.DEFAULT_JMS_COMPONENT_NAME, component);
        camelContext.start();
    }

    public IConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(IConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public CamelContext getCamelContext() {
        return camelContext;
    }

    public void setCamelContext(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    public IRouteFactory getRouteFactory() {
        return routeFactory;
    }

    public void setRouteFactory(IRouteFactory routeFactory) {
        this.routeFactory = routeFactory;
    }

    public IProducerFactory getProducerFactory() {
        return producerFactory;
    }

    public void setProducerFactory(IProducerFactory producerFactory) {
        this.producerFactory = producerFactory;
    }

    public IProducerPool getProducerPool() {
        return producerPool;
    }

    public void setProducerPool(IProducerPool producerPool) {
        this.producerPool = producerPool;
    }

    public IUserContext getUserContext() {
        return userContext;
    }

    public void setUserContext(IUserContext userContext) {
        this.userContext = userContext;
    }

    public String getActiveMQServerUrl() {
        return activeMQServerUrl;
    }

    public void setActiveMQServerUrl(String activeMQServerUrl) {
        this.activeMQServerUrl = activeMQServerUrl;
    }

}
