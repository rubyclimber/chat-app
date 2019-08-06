package com.ohgnarly.chatapp.service.impl;

import com.ohgnarly.chatapp.config.ChatProperties;
import com.ohgnarly.chatapp.model.*;
import com.ohgnarly.chatapp.service.ApiService;
import com.ohgnarly.chatapp.utility.RestUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    private RestUtility restUtility;
    private ChatProperties chatProperties;

    @Autowired
    public ApiServiceImpl(RestUtility restUtility, ChatProperties chatProperties) {
        this.restUtility = restUtility;
        this.chatProperties = chatProperties;
    }

    @Override
    public List<ChatUser> getChatUsers() {
        String url = chatProperties.getBaseApiUrl() + "users";

        ParameterizedTypeReference<List<ChatUser>> parameterizedTypeReference =
                new ParameterizedTypeReference<List<ChatUser>>() {};

        ResponseEntity<List<ChatUser>> response =
                restUtility.get(url, parameterizedTypeReference);

        return response.getBody();
    }

    @Override
    public List<Message> getMessages() {
        String url = chatProperties.getBaseApiUrl() + "messages";

        ParameterizedTypeReference<List<Message>> parameterizedTypeReference =
                new ParameterizedTypeReference<List<Message>>() {};

        ResponseEntity<List<Message>> response =
                restUtility.get(url, parameterizedTypeReference);

        return response.getBody();
    }

    @Override
    public List<Category> getCategories() {
        String url = chatProperties.getBaseApiUrl() + "/categories";

        ParameterizedTypeReference<List<Category>> parameterizedTypeReference =
                new ParameterizedTypeReference<List<Category>>() {};

        ResponseEntity<List<Category>> response =
                restUtility.get(url, parameterizedTypeReference);

        return response.getBody();
    }

    @Override
    public LoginResponse submitLogin(LoginRequest loginRequest) {
        String url = chatProperties.getBaseApiUrl() + "/chat-login";

        ParameterizedTypeReference<LoginResponse> loginResponseType =
                new ParameterizedTypeReference<LoginResponse>() {};

        ResponseEntity<LoginResponse> response = restUtility
                .<LoginRequest, LoginResponse>post(url, loginRequest,
                        loginResponseType);

        //return new LoginResponse();
        return response.getBody();
    }

    @Override
    public List<Message> getMessages(MessageRequest messageRequest) {
        String url = chatProperties.getBaseApiUrl() + "/messages";

        ParameterizedTypeReference<List<Message>> messageListType =
                new ParameterizedTypeReference<List<Message>>() {};

        ResponseEntity<List<Message>> response = restUtility.post(url, messageRequest, messageListType);

        return response.getBody();
    }
}
