package com.example.msglab.adapter;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WebClinet {
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> postForEntity(String url, HttpEntity<String> request, Class<String> stringClass) {
        return restTemplate.postForEntity(url, request, stringClass);
    }
}
