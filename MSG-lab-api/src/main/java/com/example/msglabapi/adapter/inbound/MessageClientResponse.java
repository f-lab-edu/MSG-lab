package com.example.msglabapi.adapter.inbound;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * FCM 등으로 push message를 전송하면 리턴되는 응답 객체입니다.
 */
public class MessageClientResponse {

    private final ResponseEntity<String> response;

    public MessageClientResponse(ResponseEntity<String> response) {
        this.response = response;
    }

    // todo(hun) : reponse에 담긴 httpCode를 Status로 변환하기
    public HttpStatus getStatus() {
        return HttpStatus.OK;
    }

    // todo(hun) : response body에 담긴 json 형태의 아이디 : 값 매핑을 String타입의 아이디로 변환하기
    public String getId() {
        return response.getBody();
    }
}
