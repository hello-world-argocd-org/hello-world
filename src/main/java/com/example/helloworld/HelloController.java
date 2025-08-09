package com.example.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    private final String sharedValue;
    private final String envValue;

    HelloController(@Value("${shared.value:}") String sharedValue, @Value("${env.value:}") String envValue) {
        this.sharedValue = sharedValue;
        this.envValue = envValue;
    }

    @GetMapping
    String helloWorld() {
        return "Shared value: [" + sharedValue + "] env value: [" + envValue + "]";
    }

    @Scheduled(fixedRate = 10_000)
    public void logValues() {
        log.info(helloWorld());
    }
}