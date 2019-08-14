package com.ohgnarly.chatapp.utility;

import com.ohgnarly.chatapp.config.ChatProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpStatus.*;

@RunWith(MockitoJUnitRunner.class)
public class RestUtilityTest {
    @InjectMocks
    private RestUtility restUtility;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ChatProperties chatProperties;

    @Test
    public void testGet() throws Throwable {
        //arrange
        String url = "url string";
        HttpEntity httpEntity = new HttpEntity(new HttpHeaders());
        ParameterizedTypeReference<String> parameterizedTypeReference =
                new ParameterizedTypeReference<String>() {};

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(),
                ArgumentMatchers.<ParameterizedTypeReference<String>>any()))
                .thenReturn(new ResponseEntity<>("response string", OK));

        //act
        ResponseEntity<String> response = restUtility.get(url,
                parameterizedTypeReference);

        //assert
        assertNotNull(response);
        assertEquals("response string", response.getBody());
    }

    @Test
    public void testPost()  throws Throwable {
        //arrange
        String url = "url string";
        ParameterizedTypeReference<String> parameterizedTypeReference =
                new ParameterizedTypeReference<String>() {};

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), eq(parameterizedTypeReference)))
                .thenReturn(new ResponseEntity<>("response string", OK));

        //act
        ResponseEntity<String> response = restUtility.post(url, "request string", parameterizedTypeReference);

        //assert
        assertNotNull(response);
        assertEquals("response string", response.getBody());
    }
}