package com.cocco.bootcamp.dto;

import java.util.List;

/**
 * Created by santi on 1/2/2017.
 */
public abstract class RestResponse {
    private List<String> messages;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
