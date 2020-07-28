package com.evil.servicea;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

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

    @RequestMapping("/{count}")
    public Flux<String> count(@PathVariable int count) {
        log.info("Receive message");
        return Flux.range(0, count)
                .subscribeOn(Schedulers.elastic())
                .map(i -> webClient.get().uri("/request/" + i).retrieve())
                .flatMap(response -> response.bodyToMono(String.class))
                .doOnNext(resp -> log.info("Receive response: {}", resp))
                .map(s -> s + "<br>");
    }
}
