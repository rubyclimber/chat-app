package com.ohgnarly.chatapp.request;

import com.ohgnarly.chatapp.model.ChatUser;

public class UserRequest {
    private ChatUser user;

    public ChatUser getUser() {
        return user;
    }

    public void setUser(ChatUser user) {
        this.user = user;
    }
}
