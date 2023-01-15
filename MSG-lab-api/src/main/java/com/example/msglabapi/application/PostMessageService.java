package com.example.msglabapi.application;

import org.springframework.stereotype.Service;

import com.example.msglabapi.application.outbound.MessageBrokerClient;
import com.example.msglabapi.application.outbound.MessageClient;
import com.example.msglabapi.domain.Message;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostMessageService {

    private final MessageClient messageClient;

    private final MessageBrokerClient messageBrokerClient;

    /**
     * 메시지를 전송하고, 데이터베이스에 저장합니다.
     *
     * @param message 전송할 메시지
     */
    public void send(final Message message) {
        messageClient.send(message);
        messageBrokerClient.send(message);
    }
}
