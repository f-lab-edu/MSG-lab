package com.example.msglabapi.application.outbound;

import com.example.msglabapi.domain.Message;

public interface MessageBrokerClient {
    void send(Message message);
}
