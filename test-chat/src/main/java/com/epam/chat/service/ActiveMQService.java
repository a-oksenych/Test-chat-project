package com.epam.chat.service;

import com.epam.chat.IConnectionFactory;
import com.epam.chat.IMessageService;
import com.epam.chat.IRouteFactory;

import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

public class ActiveMQService implements InitializingBean, IMessageService {

    Logger log = Logger.getLogger(ActiveMQService.class);

    private static final String DEFAULT_JMS_COMPONENT_NAME = "jms";
    private IConnectionFactory connectionFactory;
    private String activeMQUrl;
    private IRouteFactory routeFactory;
    private CamelContext camelContext;

    @Override
    public void addRoom(String room) {
        routeFactory.createRoute(DEFAULT_JMS_COMPONENT_NAME, room, camelContext);
    }

    @Override
    public void leaveRoom(String room) {
        try {
            // TODO check this place
            camelContext.removeRoute(room);
        } catch (Exception e) {
            log.error("Can't remove room.");
        }
    }

    public void writeMessage(String room, String message) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO think how to work with component name (not as constant)
        Component component = connectionFactory.createComponent(activeMQUrl);
        camelContext.addComponent(DEFAULT_JMS_COMPONENT_NAME, component);
    }

    public String getActiveMQUrl() {
        return activeMQUrl;
    }

    public void setActiveMQUrl(String activeMQUrl) {
        this.activeMQUrl = activeMQUrl;
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

}
