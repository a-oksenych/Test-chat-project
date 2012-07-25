package com.epam.chat;

import org.apache.camel.Component;

public interface IConnectionFactory {

    Component createComponent(String url);

}
