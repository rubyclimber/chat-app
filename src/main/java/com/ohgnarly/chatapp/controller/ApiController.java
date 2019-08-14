package com.ohgnarly.chatapp.controller;

import com.ohgnarly.chatapp.exception.ChatException;
import com.ohgnarly.chatapp.model.*;
import com.ohgnarly.chatapp.request.LoginRequest;
import com.ohgnarly.chatapp.response.LoginResponse;
import com.ohgnarly.chatapp.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ApiController {
    private ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping(value = "/users", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChatUser>> getChatUsers() throws ChatException {
        List<ChatUser> chatUsers = this.apiService.getChatUsers();
        return new ResponseEntity<>(chatUsers, OK);
    }

    @GetMapping(value = "/messages", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Message>> getMessages() throws ChatException {
        List<Message> messages = this.apiService.getMessages();
        return new ResponseEntity<>(messages, OK);
    }

    @GetMapping(value = "/categories", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getCategories() throws ChatException {
        List<Category> categories = this.apiService.getCategories();
        return new ResponseEntity<>(categories, OK);
    }

    @PostMapping(value = "/chat-login", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> submitLogin(@RequestBody LoginRequest loginRequest) throws ChatException {
        LoginResponse loginResponse = apiService.submitLogin(loginRequest);
        return new ResponseEntity<>(loginResponse, OK);
    }

    @PostMapping(value = "/messages", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Message>> getMessages(@RequestBody MessageRequest messageRequest) throws ChatException {
        List<Message> messages = apiService.getMessages(messageRequest);
        return new ResponseEntity<>(messages, OK);
    }
}
