package com.evil.servicec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RequestMapping
public class Application {

    @Value("${latency:30}")
    private int latency;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping
    public String index(@RequestHeader String payload) throws InterruptedException {
        Thread.sleep(latency);
        return payload;
    }
}
