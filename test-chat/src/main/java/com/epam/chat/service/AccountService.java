package com.epam.chat.service;

import com.copyright.rup.chat.common.Account;

import org.apache.http.HttpResponse;

public class AccountService extends RestService<Account> {

    public static final String entityName = "account/";
    public static final String host = "http://localhost:8081/";

    @Override
    protected String getEntityName() {
        return entityName;
    }

    @Override
    protected String getHost() {
        return host;
    }

    @Override
    protected Account parseJson(HttpResponse resultText) {
        return new Account();
    }

    @Override
    protected String buildJson(Account entity) {
        return "{}";
    }
}
