package com.copyright.rup.rest;

/**
 * Base rest constants (status codes and messages).
 * <p/>
 * Copyright (C) 2012 copyright.com
 * <p/>
 * Date: Apr 19, 2012
 * 
 * @author Vitali Kviatkouski
 */
public final class BaseRestConstants {

    private BaseRestConstants() {
    }

    // Base rest codes
    /** Success code. */
    public static final String CODE_SUCCESS = "SUCCESS";
    /** Bad argument code. */
    public static final String CODE_BAD_PARAMETER = "BAD_PARAMETER";
    /** Not found code. */
    public static final String CODE_NOT_FOUND = "NOT_FOUND";
    // Base rest messages
    /** Success message. */
    public static final String MSG_SUCCESS = "OK";
    /** Bad parameter message. */
    public static final String MSG_BAD_PARAMETER = "Bad parameter [{0}] supplied";
    /** Not found message. */
    public static final String MSG_NOT_FOUND = "Object [{0}] not found";

    // Base response constants
    /** Response property name. */
    public static final String RESPONSE_PROP_NAME = "response";
    /** Status property name. */
    public static final String STATUS_PROP_NAME = "status";
    /** Status code property name. */
    public static final String STATUS_CODE_PROP_NAME = "code";
    /** Status message property name. */
    public static final String STATUS_MESSAGE_PROP_NAME = "message";
    /** Content message property name. */
    public static final String CONTENT_PROP_NAME = "content";
}
