package com.evil.serviceb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RestControllers {


    @Value("${destination.service.url}")
    private String url;

    private final RestTemplate restTemplate;

    @RequestMapping
    public String index() {
        log.info("Receive message");
        return restTemplate.exchange(url, GET, HttpEntity.EMPTY, String.class).getBody();
    }
}
