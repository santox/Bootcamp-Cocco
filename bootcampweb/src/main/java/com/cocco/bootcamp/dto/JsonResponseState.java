package com.cocco.bootcamp.dto;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by santi on 1/2/2017.
 */
public class JsonResponseState {
    @JsonProperty("RestResponse")
    private RestResponseState restResponseState;

    public JsonResponseState() {
    }

    public RestResponseState getRestResponseState() {
        return restResponseState;
    }

    public void setRestResponseState(RestResponseState restResponseState) {
        this.restResponseState = restResponseState;
    }
}
