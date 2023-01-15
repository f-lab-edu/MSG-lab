package com.example.msglabapi.adapter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@ConfigurationProperties(prefix = "fcm")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ConfigFcm {
    private String auth;
    private String url;
}
