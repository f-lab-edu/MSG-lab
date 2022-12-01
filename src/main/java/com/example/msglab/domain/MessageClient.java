package com.example.msglab.domain;

public interface MessageClient {

    /**
     * @param message 전송할 메세지
     */
    void send(Message message);
}
