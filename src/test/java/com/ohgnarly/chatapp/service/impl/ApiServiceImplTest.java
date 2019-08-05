package com.ohgnarly.chatapp.service.impl;

import com.ohgnarly.chatapp.config.ChatProperties;
import com.ohgnarly.chatapp.model.*;
import com.ohgnarly.chatapp.utility.RestUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class ApiServiceImplTest {
    @InjectMocks
    private ApiServiceImpl apiService;

    @Mock
    private RestUtility mockRestUtility;

    @Mock
    private ChatProperties chatProperties;

    @Test
    public void testGetChatUsers() {
        //arrange
        ChatUser chatUser = new ChatUser();
        when(mockRestUtility.get(anyString(), any()))
                .thenReturn(new ResponseEntity<>(Collections.singletonList(chatUser), OK));

        //act
        List<ChatUser> chatUsers = apiService.getChatUsers();

        //assert
        assertNotNull(chatUsers);
        assertEquals(1, chatUsers.size());
        assertEquals(chatUser, chatUsers.get(0));
    }

    @Test
    public void testGetMessages() {
        //arrange
        Message message = new Message();
        when(mockRestUtility.get(anyString(), any()))
                .thenReturn(new ResponseEntity<>(Collections.singletonList(message), OK));

        //act
        List<Message> messages = apiService.getMessages();

        //assert
        assertNotNull(messages);
        assertEquals(1, messages.size());
        assertEquals(message, messages.get(0));
    }

    @Test
    public void testGetCategories() {
        //arrange
        Category category = new Category();
        when(mockRestUtility.get(anyString(), any()))
                .thenReturn(new ResponseEntity<>(Collections.singletonList(category), OK));

        //act
        List<Category> categories = apiService.getCategories();

        //assert
        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals(category, categories.get(0));
    }

    @Test
    public void testSubmitLogin() {
        //arrange
        LoginRequest loginRequest = new LoginRequest();
        ResponseEntity<LoginResponse> response = new ResponseEntity<>(new LoginResponse(), OK);
        ParameterizedTypeReference<LoginResponse> loginResponseType =
                new ParameterizedTypeReference<LoginResponse>() {};

        when(mockRestUtility.post(anyString(), eq(loginRequest), eq(loginResponseType)))
                .thenReturn(response);

        //act
        LoginResponse loginResponse = apiService.submitLogin(loginRequest);

        //assert
        assertEquals(response.getBody(), loginResponse);
    }

    @Test
    public void testSearchMessages() {
        //arrange
        MessageRequest messageRequest = new MessageRequest();
        Message message = new Message();

        ParameterizedTypeReference<List<Message>> messageListType =
                new ParameterizedTypeReference<List<Message>>() {};

        when(mockRestUtility.post(anyString(), eq(messageRequest), eq(messageListType)))
                .thenReturn(new ResponseEntity<>(Collections.singletonList(message), OK));

        //act
        List<Message> messages = apiService.getMessages(messageRequest);

        //assert
        assertNotNull(messages);
        assertTrue(messages.size() > 0);
        assertEquals(message, messages.get(0));
    }
}