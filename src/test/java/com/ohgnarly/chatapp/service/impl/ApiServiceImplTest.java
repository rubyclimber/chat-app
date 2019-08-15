package com.ohgnarly.chatapp.service.impl;

import com.ohgnarly.chatapp.config.ChatProperties;
import com.ohgnarly.chatapp.exception.ChatException;
import com.ohgnarly.chatapp.model.*;
import com.ohgnarly.chatapp.request.LoginRequest;
import com.ohgnarly.chatapp.response.CategoriesResponse;
import com.ohgnarly.chatapp.response.LoginResponse;
import com.ohgnarly.chatapp.response.MessagesResponse;
import com.ohgnarly.chatapp.response.UsersResponse;
import com.ohgnarly.chatapp.utility.RestUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import javax.xml.ws.http.HTTPException;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;
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
    public void testGetChatUsers() throws Throwable {
        //arrange
        ChatUser chatUser = new ChatUser();
        UsersResponse usersResponse = new UsersResponse();
        usersResponse.setUsers(singletonList(chatUser));

        when(mockRestUtility.get(anyString(), any()))
                .thenReturn(new ResponseEntity<>(usersResponse, OK));

        //act
        List<ChatUser> chatUsers = apiService.getChatUsers();

        //assert
        assertNotNull(chatUsers);
        assertEquals(1, chatUsers.size());
        assertEquals(chatUser, chatUsers.get(0));
    }

    @Test(expected = ChatException.class)
    public void testGetChatUsers_GivenRestException() throws Throwable {
        //arrange
        when(mockRestUtility.get(anyString(), any()))
                .thenThrow(ChatException.class);

        //act
        apiService.getChatUsers();
    }

    @Test
    public void testGetMessages() throws Throwable {
        //arrange
        int pageNumber = 1;
        Message message = new Message();
        MessagesResponse messagesResponse = new MessagesResponse();
        messagesResponse.setMessages(singletonList(message));

        when(mockRestUtility.get(anyString(), any()))
                .thenReturn(new ResponseEntity<>(messagesResponse, OK));

        //act
        List<Message> messages = apiService.getMessages(pageNumber);

        //assert
        assertNotNull(messages);
        assertEquals(1, messages.size());
        assertEquals(message, messages.get(0));
    }

    @Test(expected = ChatException.class)
    public void testGetMessages_GivenRestException() throws Throwable {
        //arrange
        when(mockRestUtility.get(anyString(), any()))
                .thenThrow(ChatException.class);
        //act
        apiService.getMessages(1);
    }

    @Test
    public void testGetCategories() throws Throwable {
        //arrange
        Category category = new Category();
        CategoriesResponse categoriesResponse = new CategoriesResponse();
        categoriesResponse.setCategories(singletonList(category));

        when(mockRestUtility.get(anyString(), any()))
                .thenReturn(new ResponseEntity<>(categoriesResponse, OK));

        //act
        List<Category> categories = apiService.getCategories();

        //assert
        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals(category, categories.get(0));
    }

    @Test(expected = ChatException.class)
    public void testGetCategories_GivenRestException() throws Throwable {
        //arrange
        Category category = new Category();
        CategoriesResponse categoriesResponse = new CategoriesResponse();
        categoriesResponse.setCategories(singletonList(category));

        when(mockRestUtility.get(anyString(), any()))
                .thenThrow(ChatException.class);

        //act
        apiService.getCategories();
    }

    @Test
    public void testSubmitLogin() throws Throwable {
        //arrange
        LoginRequest loginRequest = new LoginRequest();
        LoginResponse loginResponse = new LoginResponse();
        ParameterizedTypeReference<LoginResponse> loginResponseType =
                new ParameterizedTypeReference<LoginResponse>() {};

        when(mockRestUtility.post(anyString(), eq(loginRequest), eq(loginResponseType)))
                .thenReturn(new ResponseEntity<>(loginResponse, OK));

        //act
        LoginResponse actualLoginResponse = apiService.submitLogin(loginRequest);

        //assert
        assertEquals(loginResponse, actualLoginResponse);
    }

    @Test(expected = ChatException.class)
    public void testSubmitLogin_GivenRestException() throws Throwable {
        //arrange
        LoginRequest loginRequest = new LoginRequest();
        LoginResponse loginResponse = new LoginResponse();
        ParameterizedTypeReference<LoginResponse> loginResponseType =
                new ParameterizedTypeReference<LoginResponse>() {};

        when(mockRestUtility.post(anyString(), eq(loginRequest), eq(loginResponseType)))
                .thenThrow(ChatException.class);

        //act
        apiService.submitLogin(loginRequest);
    }

    @Test
    public void testSearchMessages() throws Throwable {
        //arrange
        MessageRequest messageRequest = new MessageRequest();
        Message message = new Message();
        MessagesResponse messagesResponse = new MessagesResponse();
        messagesResponse.setMessages(singletonList(message));

        ParameterizedTypeReference<MessagesResponse> messageListType =
                new ParameterizedTypeReference<MessagesResponse>() {};

        when(mockRestUtility.post(anyString(), eq(messageRequest), eq(messageListType)))
                .thenReturn(new ResponseEntity<>(messagesResponse, OK));

        //act
        List<Message> messages = apiService.getMessages(messageRequest);

        //assert
        assertNotNull(messages);
        assertTrue(messages.size() > 0);
        assertEquals(message, messages.get(0));
    }

    @Test(expected = ChatException.class)
    public void testSearchMessages_GivenRestException() throws Throwable {
        //arrange
        MessageRequest messageRequest = new MessageRequest();
        Message message = new Message();
        MessagesResponse messagesResponse = new MessagesResponse();
        messagesResponse.setMessages(singletonList(message));

        ParameterizedTypeReference<MessagesResponse> messageListType =
                new ParameterizedTypeReference<MessagesResponse>() {};

        when(mockRestUtility.post(anyString(), eq(messageRequest), eq(messageListType)))
                .thenThrow(ChatException.class);

        //act
        apiService.getMessages(messageRequest);
    }
}