package com.example.msglabapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
@EnableFeignClients
public class MsgLabApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsgLabApiApplication.class, args);
    }
}
