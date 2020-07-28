package com.evil.serviceb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RestControllers {

    private final WebClient webClient;

    @RequestMapping
    public Mono<String> index() {
        log.info("Receive message");
        return webClient.get().retrieve().bodyToMono(String.class);
    }


    @RequestMapping("request/{id}")
    public Mono<String> index(@PathVariable int id) {
        log.info("Receive message");
        return webClient.get().uri("/request/" + id).retrieve().bodyToMono(String.class);
    }
}
