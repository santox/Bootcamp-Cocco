package com.cocco.bootcamp.dto;

import java.util.List;

/**
 * Created by santi on 1/2/2017.
 */
public class RestResponseCountry {
    private List<String> messages;
    private List<WSCountryDTO> result;

    public RestResponseCountry() {
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<WSCountryDTO> getResult() {
        return result;
    }

    public void setResult(List<WSCountryDTO> result) {
        this.result = result;
    }
}
