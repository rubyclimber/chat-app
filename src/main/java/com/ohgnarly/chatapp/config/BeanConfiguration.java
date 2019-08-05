package com.ohgnarly.chatapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static java.lang.System.getenv;

@Configuration
@EnableTransactionManagement
public class BeanConfiguration {
    @Bean
    public ChatProperties chatProperties() {
        ChatProperties chatProperties = new ChatProperties();
        chatProperties.setBaseApiUrl(getenv("BASE_API_URL"));
        chatProperties.setClientId(getenv("API_CLIENT_ID"));
        chatProperties.setClientKey(getenv("API_CLIENT_KEY"));
        return chatProperties;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
