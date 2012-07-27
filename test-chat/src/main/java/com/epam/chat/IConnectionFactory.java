package com.epam.chat;

import org.apache.camel.Component;

/**
 * @author Maksym_Labazov
 * 
 */
public interface IConnectionFactory {

    /**
     * @param url s
     * @return sad a
     */
    Component createComponent(String url);

}
