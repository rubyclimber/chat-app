package com.ohgnarly.chatapp.controller;

import com.ohgnarly.chatapp.model.*;
import com.ohgnarly.chatapp.request.LoginRequest;
import com.ohgnarly.chatapp.response.LoginResponse;
import com.ohgnarly.chatapp.service.ApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

@RunWith(MockitoJUnitRunner.class)
public class ApiControllerTest {
    @InjectMocks
    private ApiController apiController;

    @Mock
    private ApiService apiService;

    @Test
    public void testGetChatUsers() throws Throwable {
        //arrange
        ChatUser chatUser = new ChatUser();
        when(apiService.getChatUsers()).thenReturn(singletonList(chatUser));

        //act
        ResponseEntity<List<ChatUser>> response = apiController.getChatUsers();

        //assert
        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(chatUser, response.getBody().get(0));
    }

    @Test
    public void testGetMessages() throws Throwable {
        //arrange
        Message message = new Message();
        when(apiService.getMessages(anyInt())).thenReturn(singletonList(message));

        //act
        ResponseEntity<List<Message>> response = apiController.getMessages(0);

        //assert
        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(message, response.getBody().get(0));
    }

    @Test
    public void testGetCategories() throws Throwable {
        //arrange
        Category category = new Category();
        when(apiService.getCategories()).thenReturn(singletonList(category));

        //act
        ResponseEntity<List<Category>> response = apiController.getCategories();

        //assert
        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(category, response.getBody().get(0));
    }

    @Test
    public void testSubmitLogin() throws Throwable {
        //arrange
        LoginRequest loginRequest = new LoginRequest();
        LoginResponse loginResponse = new LoginResponse();
        when(apiService.submitLogin(loginRequest)).thenReturn(loginResponse);

        //act
        ResponseEntity<LoginResponse> response = apiController.submitLogin(loginRequest);

        //assert
        assertNotNull(response);
        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(loginResponse, response.getBody());
    }

    @Test
    public void testGetMessages_GivenMessageRequest() throws Throwable {
        //arrange
        MessageRequest messageRequest = new MessageRequest();
        Message message = new Message();
        when(apiService.getMessages(messageRequest)).thenReturn(singletonList(message));

        //act
        ResponseEntity<List<Message>> response = apiController.getMessages(messageRequest);

        //assert
        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(message, response.getBody().get(0));
    }
}
