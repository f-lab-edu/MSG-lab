package com.example.msglab.application;

import com.example.msglab.domain.Message;
import com.example.msglab.domain.MessageClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final Logger logger = LoggerFactory.getLogger(MessageService.class);
    private final MessageClient messageClient;
    public void send(Message message) {
        messageClient.send(message);
    }
}
