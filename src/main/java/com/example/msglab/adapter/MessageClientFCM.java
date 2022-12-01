package com.example.msglab.adapter;

import com.example.msglab.adapter.config.ConfigFCM;
import com.example.msglab.domain.MessageClient;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
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
    private HttpHeaders headers = new HttpHeaders();

    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    private void init() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", configFCM.getAuth());
    }

    @Override
    public void send(String message) {
        HttpEntity<String> request = createRequest(message);
        ResponseEntity<String> response = postRequest(request);
        // todo(hun) : post 요청이 실패하면 retry하는 로직 구현하기
    }

    private ResponseEntity<String> postRequest(HttpEntity<String> request) {
        return restTemplate.postForEntity(configFCM.getUrl(), request, String.class);
    }

    private HttpEntity<String> createRequest(String message) {
        return new HttpEntity<>(message, headers);
    }
}
