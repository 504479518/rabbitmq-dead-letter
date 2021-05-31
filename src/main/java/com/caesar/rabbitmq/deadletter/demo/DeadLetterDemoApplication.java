package com.caesar.rabbitmq.deadletter.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Nicolas Caesar
 */
@SpringBootApplication
public class DeadLetterDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeadLetterDemoApplication.class, args);
    }

}
