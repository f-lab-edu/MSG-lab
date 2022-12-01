package com.example.msglab;

import com.example.msglab.adapter.config.ConfigFCM;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigFCM.class)
public class MsgLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsgLabApplication.class, args);
    }

}
