package com.example.msglab.adapter.outbound;

import com.example.msglab.adapter.config.ConfigFCM;
import com.example.msglab.domain.Message;
import com.example.msglab.application.outbound.MessageClient;

import javax.annotation.PostConstruct;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * FCM으로 push message 발송하는 구현체
 */
@Component
@RequiredArgsConstructor
public class MessageClientFCM implements MessageClient {

    private final ConfigFCM configFCM;

    private final HttpHeaders headers = new HttpHeaders();

    private final RestTemplateBuilder builder;

    @PostConstruct
    final void init() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", configFCM.getAuth());
    }

    @Override
    public void send(Message message) {
        final MessageRequestFcmV1 messageRequestFCM = MessageRequestFcmV1.from(message);
        final String data = messageRequestFCM.toJson();
        final HttpEntity<String> request = createRequest(data);
        ResponseEntity<String> response = postRequest(request);
    }

    private HttpEntity<String> createRequest(String message) {
        return new HttpEntity<>(message, headers);
    }

    @Timed(histogram = true)
    private ResponseEntity<String> postRequest(HttpEntity<String> request) {
        final RestTemplate restTemplate = builder.build();
        return restTemplate.postForEntity(configFCM.getUrl(), request, String.class);
    }
}
