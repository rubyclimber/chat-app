package com.ohgnarly.chatapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.util.Collections;

@SpringBootApplication
@ServletComponentScan
public class ChatAppApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ChatAppApplication.class);
        String port = System.getenv("PORT");
        System.out.println("Aaron" + port);
        app.setDefaultProperties(Collections.singletonMap("server.port", System.getenv("PORT")));
        app.run(args);
    }
}
