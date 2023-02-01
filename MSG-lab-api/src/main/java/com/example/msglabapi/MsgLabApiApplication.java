package com.example.msglabapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class MsgLabApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsgLabApiApplication.class, args);
    }
}
