package com.epam.chat;

import org.apache.camel.CamelContext;

/**
 * @author Maksym_Labazov
 * 
 */
public interface IRouteFactory {

    /**
     * @param componentName s
     * @param room s
     * @param clientId s
     * @param context s
     */
    void createRoute(String componentName, String room, String clientId, CamelContext context);

}
