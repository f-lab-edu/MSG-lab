package com.example.msglabapi.adapter.config;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@ConstructorBinding
@ConfigurationProperties("fcm")
@Validated
public class PropertyFcm {
    @NotEmpty
    private final String auth;
    @NotEmpty
    private final String url;
}
