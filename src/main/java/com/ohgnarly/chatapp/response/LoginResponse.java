package com.ohgnarly.chatapp.response;

import com.ohgnarly.chatapp.model.ChatUser;

public class LoginResponse {
    private boolean success;
    private ChatUser user;
    private String socketUrl;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ChatUser getUser() {
        return user;
    }

    public void setUser(ChatUser user) {
        this.user = user;
    }

    public String getSocketUrl() {
        return socketUrl;
    }

    public void setSocketUrl(String socketUrl) {
        this.socketUrl = socketUrl;
    }
}
