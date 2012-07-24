package com.epam.chat;

import org.apache.camel.CamelContext;

public interface IRouteFactory {

    void createRoute(String componentName, String room, CamelContext context);

}
