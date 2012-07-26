package com.copyright.rup.rest;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

/**
 * Base rest resource. Contains base functionality to wrap business object into standard protocol wrapper:
 * 
 * <pre>
 *  {
 *      status : {
 *          code: "SUCCESS", 
 *          message: "OK"
 *      },
 *      content : {
 *          ...data goes here...
 *      }
 *  }
 * </pre>
 * 
 * <p/>
 * Copyright (C) 2012 copyright.com
 * <p/>
 * Date: Apr 18, 2012
 * 
 * @author Vitali Kviatkouski
 */
public class BaseRestResource {

    private static final String VIEW_NAME = "restView";

    /**
     * Wraps business object into success response (code:"SUCCESS", message:"OK").
     * 
     * @param businessObject business object to wrap.
     * @return filled model and view object.
     */
    protected ModelAndView createSuccessResponse(final Object businessObject) {
        return createModelAndView(wrapResponse(DefaultResponseStatus.SUCCESS, businessObject));
    }

    /**
     * Creates bad parameter response. Does not return any business object.
     * 
     * @param parameter name or parameter which is considered bad.
     * @return rest response with 'bad parameter' code and message.
     */
    protected ModelAndView createBadParameterResponse(final String parameter) {
        final RestResponse response = wrapResponse(DefaultResponseStatus.BAD_PARAMETER, null, parameter);
        return createModelAndView(response);
    }

    /**
     * Creates not found response. Does not return any business object.
     * 
     * @param objId id of object which wasn't found.
     * @return rest response with 'not found' code and message.
     */
    protected ModelAndView createNotFoundResponse(final Object objId) {
        final RestResponse response = wrapResponse(DefaultResponseStatus.NOT_FOUND, null, objId);
        return createModelAndView(response);
    }

    /**
     * Wraps business object into response with given response code and message.
     * 
     * @param statusCode status code.
     * @param statusMsg status message.
     * @param businessObject business object.
     * @return filled model and view object.
     */
    protected ModelAndView createResponse(final String statusCode, final String statusMsg,
            Object businessObject) {
        final RestResponse response = new RestResponse(statusCode, statusMsg, businessObject);
        return createModelAndView(response);
    }

    /**
     * Creates response with given response code and message. No content returned, all required info passed
     * via status message and code.
     * 
     * @param statusCode status code.
     * @param statusMsg status message.
     * @return filled model and view object.
     */
    protected ModelAndView createResponse(final String statusCode, final String statusMsg) {
        return createResponse(statusCode, statusMsg, null);
    }

    private ModelAndView createModelAndView(RestResponse response) {
        final ModelAndView mav = new ModelAndView(VIEW_NAME);
        mav.addObject(BaseRestConstants.RESPONSE_PROP_NAME, response);
        return mav;
    }

    private RestResponse wrapResponse(DefaultResponseStatus status, Object businessObject,
            Object... formatParams) {
        final RestResponseStatus respStatus = status.getStatus(formatParams);
        return new RestResponse(respStatus, businessObject);
    }
}
