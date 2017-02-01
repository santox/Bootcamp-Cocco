package com.cocco.bootcamp.dto;

import java.util.List;

/**
 * Created by santi on 1/2/2017.
 */
public class RestResponseState extends RestResponse {
    private List<WSStateDTO> result;

    public RestResponseState() {
    }

    public List<WSStateDTO> getResult() {
        return result;
    }

    public void setResult(List<WSStateDTO> result) {
        this.result = result;
    }
}
