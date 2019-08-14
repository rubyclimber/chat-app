package com.ohgnarly.chatapp.response;

import com.ohgnarly.chatapp.model.ChatUser;

import java.util.List;

public class UsersResponse {
    private List<ChatUser> users;

    public List<ChatUser> getUsers() {
        return users;
    }

    public void setUsers(List<ChatUser> users) {
        this.users = users;
    }
}
