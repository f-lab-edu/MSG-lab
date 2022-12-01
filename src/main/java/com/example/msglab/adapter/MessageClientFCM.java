package com.example.msglab.adapter;

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

/**
 * FCM으로 push message 발송하는 구현체
 */
@Component
@RequiredArgsConstructor
public class MessageClientFCM implements MessageClient {

    private final ConfigFCM configFCM;
    private HttpHeaders headers = new HttpHeaders();

    private final WebClinet webClinet;
    private final ObjectMapper mapper = new ObjectMapper();


    @PostConstruct
    final void init() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", configFCM.getAuth());
    }

    @Override
    public void send(Message message) {
        String data = convertMessage2Json(message);
        HttpEntity<String> request = createRequest(data);
        ResponseEntity<String> response = postRequest(request);
        // todo(hun) : post 요청이 실패하면 retry하는 로직 구현하기
    }

    private String convertMessage2Json(Message message) {
        String data = "";
        try {
            data = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new HttpMessageConversionException(e.getMessage());
        }
        return data;
    }

    private HttpEntity<String> createRequest(String message) {
        return new HttpEntity<>(message, headers);
    }

    private ResponseEntity<String> postRequest(HttpEntity<String> request) {
        return webClinet.postForEntity(configFCM.getUrl(), request, String.class);
    }
}
