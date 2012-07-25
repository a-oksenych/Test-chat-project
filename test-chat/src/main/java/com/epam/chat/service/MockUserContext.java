package com.epam.chat.service;

import com.epam.chat.IUserContext;

public class MockUserContext implements IUserContext {

    private String clientId = "123";

    @Override
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}
