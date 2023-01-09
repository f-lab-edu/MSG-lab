package com.example.msglab.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.msglab.application.outbound.MessageBrokerClient;
import com.example.msglab.application.outbound.MessageClient;
import com.example.msglab.domain.Message;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostMessageService {

    private final Logger logger = LoggerFactory.getLogger(PostMessageService.class);
    private final MessageClient messageClient;

    private final MessageBrokerClient messageBrokerClient;

    /**
     * push-message를 전송하고, 데이터베이스에 저장합니다.
     *
     * @param message push-message로 전송할 메세지
     * @return 저장된 메세지
     */
    public void send(final Message message) {
        messageClient.send(message);
        messageBrokerClient.send(message);
    }
}
