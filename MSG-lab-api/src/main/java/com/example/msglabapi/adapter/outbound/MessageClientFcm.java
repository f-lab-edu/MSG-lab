package com.example.msglabapi.adapter.outbound;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.msglabapi.adapter.config.ConfigFcm;
import com.example.msglabapi.application.outbound.MessageClient;
import com.example.msglabapi.domain.Message;

import lombok.RequiredArgsConstructor;

/**
 * FCM으로 push message 발송하는 구현체
 */
@Component
@RequiredArgsConstructor
public class MessageClientFcm implements MessageClient {

    private final ConfigFcm configFCM;

    private final HttpHeaders headers = new HttpHeaders();

    private final RestTemplate restTemplate;

    @PostConstruct
    final void init() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", configFCM.getAuth());
    }

    @Override
    public void send(Message message) {
        final MessageRequestFcmV1 messageRequestFCM = MessageRequestFcmV1.from(message);
        final RequestEntity<MessageRequestFcmV1> request = getRequest(messageRequestFCM);
        post(request);
    }

    private RequestEntity<MessageRequestFcmV1> getRequest(
            MessageRequestFcmV1 messageRequestFCM) {
        return RequestEntity
                .post(configFCM.getUrl())
                .headers(headers)
                .body(messageRequestFCM);
    }

    private void post(RequestEntity<MessageRequestFcmV1> request) {
        restTemplate.exchange(request, String.class);
    }
}
