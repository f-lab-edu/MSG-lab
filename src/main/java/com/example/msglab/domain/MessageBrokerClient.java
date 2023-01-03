package com.example.msglab.domain;

public interface MessageBrokerClient {
    void send(Message message);
}
