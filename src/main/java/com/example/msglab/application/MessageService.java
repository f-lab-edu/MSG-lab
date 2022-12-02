package com.example.msglab.application;

import com.example.msglab.adapter.MessageRepository;
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

    private final MessageRepository repository;

    /**
     * push-message를 전송하고, 데이터베이스에 저장합니다.
     *
     * @param message push-message로 전송할 메세지
     */
    public void send(Message message) {
        messageClient.send(message);
        message.generateID();
        // todo(hun): jpaRepository 인터페이스입니다. 서비스에서 직접 호출해도 될까요?
        repository.save(message);
    }
}
