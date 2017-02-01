package com.cocco.bootcamp.dto;

import java.util.List;

/**
 * Created by santi on 1/2/2017.
 */
public class RestResponseCountry extends RestResponse {
    private List<WSCountryDTO> result;

    public RestResponseCountry() {
    }

    public List<WSCountryDTO> getResult() {
        return result;
    }

    public void setResult(List<WSCountryDTO> result) {
        this.result = result;
    }
}
