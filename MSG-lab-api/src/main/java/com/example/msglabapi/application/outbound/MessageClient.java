package com.example.msglabapi.application.outbound;

import com.example.msglabapi.domain.Message;

public interface MessageClient {

    /**
     * @param message 전송할 메세지
     */
    void send(Message message);
}
