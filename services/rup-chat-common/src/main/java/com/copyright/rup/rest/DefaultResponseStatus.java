package com.copyright.rup.rest;

import static com.copyright.rup.rest.BaseRestConstants.*;

import java.text.MessageFormat;

/**
 * Default response statuses.
 * <p/>
 * Copyright (C) 2012 copyright.com
 * <p/>
 * Date: Apr 19, 2012
 * 
 * @author Vitali Kviatkouski
 */
public enum DefaultResponseStatus {
    /** Success status. */
    SUCCESS(new RestResponseStatus(CODE_SUCCESS, MSG_SUCCESS)),
    /** Bad parameter status. */
    BAD_PARAMETER(new RestResponseStatus(CODE_BAD_PARAMETER, MSG_BAD_PARAMETER)),
    /** Not found status. */
    NOT_FOUND(new RestResponseStatus(CODE_NOT_FOUND, MSG_NOT_FOUND));

    private RestResponseStatus status;

    private DefaultResponseStatus(RestResponseStatus status) {
        this.status = status;
    }

    /**
     * Returns {@link RestResponseStatus}, also formats message if format parameters are supplied.
     * 
     * @param formatParameters format parameters.
     * @return {@link RestResponseStatus} instance.
     */
    public RestResponseStatus getStatus(Object... formatParameters) {
        if (formatParameters == null) {
            return status;
        }
        String formattedMessage = MessageFormat.format(status.getMessage(), formatParameters);
        return new RestResponseStatus(status.getCode(), formattedMessage);
    }
}
