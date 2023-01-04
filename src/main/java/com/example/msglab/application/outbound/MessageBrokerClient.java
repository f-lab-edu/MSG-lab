package com.example.msglab.application.outbound;

import com.example.msglab.domain.Message;

public interface MessageBrokerClient {
    void send(Message message);
}
