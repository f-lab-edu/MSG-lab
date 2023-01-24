package com.example.msglab.common;

import static org.mockito.Mockito.mock;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeRestTemplateBuilder extends RestTemplateBuilder {
    private RestTemplate restTemplate = mock(RestTemplate.class);

    @Override
    public RestTemplate build() {
        return restTemplate;
    }

    @Override
    public RestTemplateBuilder setConnectTimeout(Duration connectTimeout) {
        return this;
    }

    @Override
    public RestTemplateBuilder setReadTimeout(Duration readTimeout) {
        return this;
    }
}
