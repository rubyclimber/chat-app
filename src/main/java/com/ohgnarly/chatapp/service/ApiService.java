package com.ohgnarly.chatapp.service;

import com.ohgnarly.chatapp.model.*;

import java.util.List;

public interface ApiService {
    List<ChatUser> getChatUsers();

    List<Message> getMessages();

    List<Category> getCategories();

    LoginResponse submitLogin(LoginRequest loginRequest);

    List<Message> getMessages(MessageRequest messageRequest);
}
