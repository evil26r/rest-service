package com.evil.servicec;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import static java.time.temporal.ChronoUnit.MILLIS;

@Slf4j
@SpringBootApplication
@RestController
public class Application {

    @Value("${latency:30000}")
    private int latency;

    AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping
    public Mono<String> index() {
        //  Вернем строку
        return Mono.just("I'm so slow")
                //  Отправим обработку в другой поток пула эластик
                .subscribeOn(Schedulers.elastic())
                // Выведем в лог
                .doOnNext(s -> log.info("I'm so busy. Request #: {}", counter.getAndIncrement()))
                //  Сделаем сложную работу - подождем
                .delayElement(Duration.of(latency, MILLIS));
    }

    @RequestMapping("/request/{id}")
    public Mono<String> index(@PathVariable int id) {
        //  Вернем строку
        return Mono.just("I'm so slow. Request #: " + id)
                //  Отправим обработку в другой поток пула эластик
                .subscribeOn(Schedulers.elastic())
                // Выведем в лог
                .doOnNext(s -> log.info("I'm so busy. Request #: {}", id))
                //  Сделаем сложную работу - подождем
                .delayElement(Duration.of(latency, MILLIS));
    }
}
