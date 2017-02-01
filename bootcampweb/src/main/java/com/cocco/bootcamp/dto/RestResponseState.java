package com.cocco.bootcamp.dto;

import java.util.List;

/**
 * Created by santi on 1/2/2017.
 */
public class RestResponseState {
    private List<String> messages;
    private List<WSStateDTO> result;

    public RestResponseState() {
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<WSStateDTO> getResult() {
        return result;
    }

    public void setResult(List<WSStateDTO> result) {
        this.result = result;
    }
}
