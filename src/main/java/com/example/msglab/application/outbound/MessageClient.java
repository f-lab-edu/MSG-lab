package com.example.msglab.application.outbound;

import com.example.msglab.domain.Message;

public interface MessageClient {

    /**
     * @param message 전송할 메세지
     */
    void send(Message message);
}
