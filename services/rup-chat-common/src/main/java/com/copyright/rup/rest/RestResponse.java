package com.copyright.rup.rest;

/**
 * Rest response wrapper object. Immutable.
 * <p/>
 * Copyright (C) 2012 copyright.com
 * <p/>
 * Date: Apr 18, 2012
 * 
 * @author Vitali Kviatkouski
 */
public class RestResponse {

    private RestResponseStatus status;
    private Object content;

    /**
     * Constructor.
     */
    public RestResponse() {
    }

    /**
     * Constructor.
     * 
     * @param status status.
     * @param businessObject business object.
     */
    public RestResponse(RestResponseStatus status, Object businessObject) {
        this.status = status;
        this.content = businessObject;
    }

    /**
     * Convenient constructor.
     * 
     * @param statusCode status code.
     * @param statusMessage status message.
     * @param businessObject business object.
     */
    public RestResponse(String statusCode, String statusMessage, Object businessObject) {
        this(new RestResponseStatus(statusCode, statusMessage), businessObject);
    }

    /**
     * Gets status.
     * 
     * @return status.
     */
    public RestResponseStatus getStatus() {
        return status;
    }

    /**
     * Gets content.
     * 
     * @return content.
     */
    public Object getContent() {
        return content;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return "RestResponse[status=" + status + "content=" + content + "]";
    }
}
