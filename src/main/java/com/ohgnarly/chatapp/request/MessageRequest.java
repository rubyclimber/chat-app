package com.ohgnarly.chatapp.request;

import com.ohgnarly.chatapp.model.Message;

public class MessageRequest {
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
