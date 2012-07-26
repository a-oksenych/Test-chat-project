package com.copyright.rup.rest;

/**
 * Rest response status.
 * <p/>
 * Copyright (C) 2012 copyright.com
 * <p/>
 * Date: Apr 19, 2012
 * 
 * @author Vitali Kviatkouski
 */
public class RestResponseStatus {

    private String code;
    private String message;

    /**
     * Constructor.
     */
    public RestResponseStatus() {
    }

    /**
     * Constructor.
     * 
     * @param code code.
     * @param message message.
     */
    public RestResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets code.
     * 
     * @return code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets message.
     * 
     * @return message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RestStatus=[code=" + code + ", message='" + message + "']";
    }
}
