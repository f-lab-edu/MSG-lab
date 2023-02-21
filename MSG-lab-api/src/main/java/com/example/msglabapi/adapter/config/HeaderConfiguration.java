package com.example.msglabapi.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import feign.RequestInterceptor;

@Configuration
public class HeaderConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor(final PropertyFcm propertyFcm) {
        return requestTemplate -> requestTemplate
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .header(HttpHeaders.AUTHORIZATION, propertyFcm.getAuth());
    }
}
