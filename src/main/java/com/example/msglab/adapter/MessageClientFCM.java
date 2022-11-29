package com.example.msglab.adapter;

import com.example.msglab.domain.MessageClientResp;
import com.example.msglab.domain.MessageClient;
import com.example.msglab.domain.MessageURL;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * FCM으로 push message 발송하는 구현체
 */
public class MessageClientFCM implements MessageClient {

    // todo(hun) : auth키를 인터넷에 공개할 수 없습니다.
    //  해결하기 위해 스프링의 프로필을 적용할 수 있을것 같습니다.
    //  좋은 방법이 있으면 알려주세요.
    private static final String auth = "";

    private static HttpHeaders headers = new HttpHeaders();

    static {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", auth);
    }

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public MessageClientResp send(String message) {
        HttpEntity<String> request = createRequest(message);
        ResponseEntity<String> response = postRequest(request);
        return new MessageClientResp(response);
    }

    private ResponseEntity<String> postRequest(HttpEntity<String> request) {
        return restTemplate.postForEntity(MessageURL.FCM.getUrl(), request, String.class);
    }

    private HttpEntity<String> createRequest(String message) {
        return new HttpEntity<>(message, headers);
    }
}
