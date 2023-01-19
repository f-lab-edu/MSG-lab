package com.example.msglabapi.adapter.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = { PropertyFcm.class })
public class ConfigurationFcm {
}
