package com.example.msglabapi.adapter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "fcm")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConfigFcm {
    private String auth;
    private String url;
}
