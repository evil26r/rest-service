package com.evil.servicec;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication
@RestController
public class Application {

    @Value("${latency:30000}")
    private int latency;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping
    public String index() throws InterruptedException {
        log.info("Receive message");
        Thread.sleep(latency);
        return "I'm so slow";
    }
}
