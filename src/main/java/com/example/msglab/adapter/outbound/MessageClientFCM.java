package com.example.msglab.adapter.outbound;

import com.example.msglab.adapter.config.ConfigFCM;
import com.example.msglab.domain.Message;
import com.example.msglab.domain.MessageClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
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

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper = new ObjectMapper();


    @PostConstruct
    final void init() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", configFCM.getAuth());
    }

    @Override
    public void send(Message message) {
        MessageRequestFcmV1 messageRequestFCM = MessageRequestFcmV1.from(message);
        String data = convertMessage2Json(messageRequestFCM);
        HttpEntity<String> request = createRequest(data);
        ResponseEntity<String> response = postRequest(request);
    }

    private String convertMessage2Json(MessageRequestFcmV1 messageRequestFCM) {
        String data = "";
        try {
            data = mapper.writeValueAsString(messageRequestFCM);
        } catch (JsonProcessingException e) {
            throw new HttpMessageConversionException(e.getMessage());
        }
        return data;
    }

    private HttpEntity<String> createRequest(String message) {
        return new HttpEntity<>(message, headers);
    }

    private ResponseEntity<String> postRequest(HttpEntity<String> request) {
        return restTemplate.postForEntity(configFCM.getUrl(), request, String.class);
    }
}
