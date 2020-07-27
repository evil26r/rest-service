package com.evil.servicea;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;

@RestController
@RequiredArgsConstructor
public class RestControllers {

    @Value("${destination.service.url}")
    private String url;

    private final RestTemplate restTemplate;

    @RequestMapping
    public String index() {
        return restTemplate.exchange(url, GET, HttpEntity.EMPTY, String.class).getBody();
    }
}
