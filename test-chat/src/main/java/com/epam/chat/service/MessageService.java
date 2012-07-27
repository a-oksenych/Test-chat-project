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

/**
 * @author Maksym_Labazov
 * 
 */
public class MessageService implements InitializingBean, IMessageService {

    private Logger log = Logger.getLogger(MessageService.class);

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

    /**
     * @return a
     */
    public IConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    /**
     * @param connectionFactory s
     */
    public void setConnectionFactory(IConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * @return asd as
     */
    public CamelContext getCamelContext() {
        return camelContext;
    }

    /**
     * @param camelContext s
     */
    public void setCamelContext(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    /**
     * @return s
     */
    public IRouteFactory getRouteFactory() {
        return routeFactory;
    }

    /**
     * @param routeFactory s
     */
    public void setRouteFactory(IRouteFactory routeFactory) {
        this.routeFactory = routeFactory;
    }

    /**
     * @return d
     */
    public IProducerFactory getProducerFactory() {
        return producerFactory;
    }

    /**
     * @param producerFactory s
     */
    public void setProducerFactory(IProducerFactory producerFactory) {
        this.producerFactory = producerFactory;
    }

    /**
     * @return as d
     */
    public IProducerPool getProducerPool() {
        return producerPool;
    }

    /**
     * @param producerPool s
     */
    public void setProducerPool(IProducerPool producerPool) {
        this.producerPool = producerPool;
    }

    /**
     * @return s
     */
    public IUserContext getUserContext() {
        return userContext;
    }

    /**
     * @param userContext s
     */
    public void setUserContext(IUserContext userContext) {
        this.userContext = userContext;
    }

    /**
     * @return s
     */
    public String getActiveMQServerUrl() {
        return activeMQServerUrl;
    }

    /**
     * @param activeMQServerUrl s
     */
    public void setActiveMQServerUrl(String activeMQServerUrl) {
        this.activeMQServerUrl = activeMQServerUrl;
    }

}
