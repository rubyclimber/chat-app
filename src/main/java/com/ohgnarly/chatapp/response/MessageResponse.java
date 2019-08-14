package com.ohgnarly.chatapp.response;

import com.ohgnarly.chatapp.model.Message;

public class MessageResponse {
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
