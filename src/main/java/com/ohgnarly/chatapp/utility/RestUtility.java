package com.ohgnarly.chatapp.utility;

import com.ohgnarly.chatapp.config.ChatProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Component
public class RestUtility {
    private RestTemplate restTemplate;
    private ChatProperties chatProperties;

    @Autowired
    public RestUtility(RestTemplate restTemplate, ChatProperties chatProperties) {
        this.chatProperties = chatProperties;
        this.restTemplate = restTemplate;
    }

    public <TResp> ResponseEntity<TResp> get(String url, ParameterizedTypeReference<TResp> parameterizedTypeReference) {
        HttpEntity httpEntity = new HttpEntity(createHttpHeaders());
        return restTemplate.exchange(url, GET, httpEntity, parameterizedTypeReference);
    }

    public <TReq, TResp> ResponseEntity<TResp> post(String url, TReq requestEntity,
                                                    ParameterizedTypeReference<TResp> parameterizedTypeReference) {
        HttpEntity<TReq> httpEntity = new HttpEntity<>(requestEntity, createHttpHeaders());
        return restTemplate.exchange(url, POST, httpEntity, parameterizedTypeReference);
    }

    private HttpHeaders createHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("sender", chatProperties.getClientId());
        httpHeaders.add("api-key", chatProperties.getClientKey());
        return httpHeaders;
    }
}
