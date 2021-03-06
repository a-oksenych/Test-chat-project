package com.epam.chat.helper;

/**
 * @author Maksym_Labazov
 * 
 */
public class MessageBuilder {

    /**
     * 
     */
    public static final String DEFAULT_JMS_COMPONENT_NAME = "jms";

    private MessageBuilder() {
    }

    /**
     * @param room s
     * @return s
     */
    public static String buildRoute(String room) {
        String result = buildRouteForConsumer(room, DEFAULT_JMS_COMPONENT_NAME, null);
        return result;
    }

    /**
     * @param room s
     * @param componentName s
     * @param clientId s
     * @return s
     */
    public static String buildRouteForConsumer(String room, String componentName, String clientId) {
        StringBuilder result = new StringBuilder();
        result.append(componentName).append(":").append(room);
        if (clientId != null) {
            result.append("?clientId=").append(clientId).append("&durableSubscriptionName=bar1");
        }
        return result.toString();
    }
}
