package com.ohgnarly.chatapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;
import static java.lang.System.getenv;

@Configuration
@EnableTransactionManagement
public class BeanConfiguration {
    @Bean
    public ChatProperties chatProperties() {
        System.out.println("Configuring ChatProperties");
        ChatProperties chatProperties = new ChatProperties();
        chatProperties.setBaseApiUrl(getenv("BASE_API_URL"));
        chatProperties.setClientId(getenv("API_CLIENT_ID"));
        chatProperties.setClientKey(getenv("API_CLIENT_KEY"));
        System.out.println(format("%s - %s - %s", chatProperties.getBaseApiUrl(), chatProperties.getClientId(),
                chatProperties.getClientKey()));
        return chatProperties;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
