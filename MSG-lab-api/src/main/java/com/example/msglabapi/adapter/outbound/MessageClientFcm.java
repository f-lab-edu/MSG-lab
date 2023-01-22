package com.example.msglabapi.adapter.outbound;

import java.time.Duration;

import javax.annotation.PostConstruct;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.msglabapi.adapter.config.PropertyFcm;
import com.example.msglabapi.application.outbound.MessageClient;
import com.example.msglabapi.domain.Message;
import com.google.common.annotations.VisibleForTesting;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageClientFcm implements MessageClient {

    private final PropertyFcm propertyFcm;

    private final HttpHeaders headers = new HttpHeaders();

    private final RestTemplateBuilder restTemplateBuilder;

    @PostConstruct
    @VisibleForTesting
    final void init() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", propertyFcm.getAuth());
    }

    @Override
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000), value = HttpServerErrorException.class)
    public void send(final Message message) {
        final MessageRequestFcmV1 messageRequestFCM = MessageRequestFcmV1.from(message);
        final RequestEntity<MessageRequestFcmV1> request = getRequest(messageRequestFCM);
        try {
            post(request);
        } catch (RestClientException e) {
            throw new HttpServerErrorException(HttpStatus.REQUEST_TIMEOUT);
        }
    }

    private RequestEntity<MessageRequestFcmV1> getRequest(
            MessageRequestFcmV1 messageRequestFCM) {
        return RequestEntity
                .post(propertyFcm.getUrl())
                .headers(headers)
                .body(messageRequestFCM);
    }

    private void post(final RequestEntity<MessageRequestFcmV1> request) {
        final RestTemplate restTemplate = restTemplateBuilder
                .setReadTimeout(Duration.ofSeconds(3000))
                .setConnectTimeout(Duration.ofSeconds(3000))
                .build();
        restTemplate.exchange(request, String.class);
    }

    @Recover
    public void recoverPost(final HttpServerErrorException e) {
        // todo(hun): 현재 retryable 메소드의 리턴값이 void입니다. 적당한 리턴 클래스를 추가해야 합니다.
    }
}
