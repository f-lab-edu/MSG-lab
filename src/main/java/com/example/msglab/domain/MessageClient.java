package com.example.msglab.domain;

public interface MessageClient {

    /**
     * @param message json 형태의 전송할 문자열
     */
    void send(String message);
}
