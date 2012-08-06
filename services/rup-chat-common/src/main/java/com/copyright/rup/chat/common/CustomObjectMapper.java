package com.copyright.rup.chat.common;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/**
 * @author Oleksandr Dekhtyar
 * 
 */
public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        super();
        configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, false);
        configure(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, false);
        configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
