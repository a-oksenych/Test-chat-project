package com.epam.chat.service;

import com.epam.chat.IUserContext;

/**
 * @author Maksym_Labazov
 * 
 */
public class MockUserContext implements IUserContext {

    private String clientId = "123";

    @Override
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId ds
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}
