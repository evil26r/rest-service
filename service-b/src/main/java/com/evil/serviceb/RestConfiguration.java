package com.evil.serviceb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestConfiguration {

    @Value("${destination.service.url}")
    private String url;

    @Bean
    public WebClient restTemplate() {
        return WebClient.create(url);
    }

}
