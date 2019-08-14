package com.ohgnarly.chatapp.response;

import com.ohgnarly.chatapp.model.ChatUser;

public class UserResponse {
    private ChatUser user;

    public ChatUser getUser() {
        return user;
    }

    public void setUser(ChatUser user) {
        this.user = user;
    }
}
