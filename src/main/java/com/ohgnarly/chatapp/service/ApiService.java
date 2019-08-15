package com.ohgnarly.chatapp.service;

import com.ohgnarly.chatapp.exception.ChatException;
import com.ohgnarly.chatapp.model.*;
import com.ohgnarly.chatapp.request.LoginRequest;
import com.ohgnarly.chatapp.response.LoginResponse;

import java.util.List;

public interface ApiService {
    List<ChatUser> getChatUsers() throws ChatException;

    List<Message> getMessages(int pageNumber) throws ChatException;

    List<Category> getCategories() throws ChatException;

    LoginResponse submitLogin(LoginRequest loginRequest) throws ChatException;

    List<Message> getMessages(MessageRequest messageRequest) throws ChatException;
}
