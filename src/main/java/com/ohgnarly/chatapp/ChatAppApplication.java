package com.ohgnarly.chatapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.util.Collections;

import static java.lang.System.getenv;
import static net.logstash.logback.encoder.org.apache.commons.lang.StringUtils.isNotBlank;

@SpringBootApplication
@ServletComponentScan
public class ChatAppApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ChatAppApplication.class);
        String port = isNotBlank(getenv("PORT"))
                ? getenv("PORT")
                : "3000";
        app.setDefaultProperties(Collections.singletonMap("server.port", port));
        app.run(args);
    }
}
