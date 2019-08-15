package com.ohgnarly.chatapp.service.impl;

import com.ohgnarly.chatapp.config.ChatProperties;
import com.ohgnarly.chatapp.exception.ChatException;
import com.ohgnarly.chatapp.model.*;
import com.ohgnarly.chatapp.request.LoginRequest;
import com.ohgnarly.chatapp.response.CategoriesResponse;
import com.ohgnarly.chatapp.response.LoginResponse;
import com.ohgnarly.chatapp.response.MessagesResponse;
import com.ohgnarly.chatapp.response.UsersResponse;
import com.ohgnarly.chatapp.service.ApiService;
import com.ohgnarly.chatapp.utility.RestUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;

import static java.lang.String.format;

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
    public List<ChatUser> getChatUsers() throws ChatException {
        String url = chatProperties.getApiBaseUrl() + "users";

        ParameterizedTypeReference<UsersResponse> parameterizedTypeReference =
                new ParameterizedTypeReference<UsersResponse>() {};

        ResponseEntity<UsersResponse> response =
                    restUtility.get(url, parameterizedTypeReference);

        return response.getBody().getUsers();
    }

    @Override
    public List<Message> getMessages(int pageNumber) throws ChatException {
        String url = format("%smessages?pageNumber=%s", chatProperties.getApiBaseUrl(), pageNumber);

        ParameterizedTypeReference<MessagesResponse> parameterizedTypeReference =
                new ParameterizedTypeReference<MessagesResponse>() {};

        ResponseEntity<MessagesResponse> response =
                restUtility.get(url, parameterizedTypeReference);

        return response.getBody().getMessages();
    }

    @Override
    public List<Category> getCategories() throws ChatException {
        String url = chatProperties.getApiBaseUrl() + "/categories";

        ParameterizedTypeReference<CategoriesResponse> parameterizedTypeReference =
                new ParameterizedTypeReference<CategoriesResponse>() {};

        ResponseEntity<CategoriesResponse> response =
                restUtility.get(url, parameterizedTypeReference);

        return response.getBody().getCategories();
    }

    @Override
    public LoginResponse submitLogin(LoginRequest loginRequest) throws ChatException {
        String url = chatProperties.getApiBaseUrl() + "/chat-login";

        ParameterizedTypeReference<LoginResponse> loginResponseType =
                new ParameterizedTypeReference<LoginResponse>() {};

        ResponseEntity<LoginResponse> response = restUtility
                .<LoginRequest, LoginResponse>post(url, loginRequest,
                        loginResponseType);

        //return new LoginResponse();
        return response.getBody();
    }

    @Override
    public List<Message> getMessages(MessageRequest messageRequest) throws ChatException {
        String url = chatProperties.getApiBaseUrl() + "/messages";

        ParameterizedTypeReference<MessagesResponse> messageListType =
                new ParameterizedTypeReference<MessagesResponse>() {};

        ResponseEntity<MessagesResponse> response = restUtility.post(url, messageRequest, messageListType);

        return response.getBody().getMessages();
    }
}
