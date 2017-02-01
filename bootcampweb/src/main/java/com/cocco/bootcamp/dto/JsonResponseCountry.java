package com.cocco.bootcamp.dto;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by santi on 1/2/2017.
 */
public class JsonResponseCountry {
    @JsonProperty("RestResponse")
    private RestResponseCountry restResponseCountry;

    public JsonResponseCountry() {
    }

    public RestResponseCountry getRestResponseCountry() {
        return restResponseCountry;
    }

    public void setRestResponseCountry(RestResponseCountry restResponseCountry) {
        this.restResponseCountry = restResponseCountry;
    }
}
