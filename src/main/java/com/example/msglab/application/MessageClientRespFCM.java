package com.example.msglab.application;

import com.example.msglab.domain.MessageClientResp;
import com.example.msglab.domain.Status;
import org.springframework.http.ResponseEntity;

public class MessageClientRespFCM implements MessageClientResp {

    private final ResponseEntity<String> response;

    public MessageClientRespFCM(ResponseEntity<String> response) {
        this.response = response;
    }
    // todo(hun) : reponse에 담긴 httpCode를 Status로 변환하기
    @Override
    public Status getStatus() {
        return Status.OK;
    }

    // todo(hun) : response body에 담긴 json 형태의 아이디 : 값 매핑을 String타입의 아이디로 변환하기
    @Override
    public String getId() {
        return response.getBody();
    }
}
