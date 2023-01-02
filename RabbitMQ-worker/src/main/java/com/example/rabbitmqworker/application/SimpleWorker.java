package com.example.rabbitmqworker.application;

import com.example.rabbitmqworker.adapter.inbound.SimpleListener;
import com.example.rabbitmqworker.adapter.outbound.MessageRepository;
import com.example.rabbitmqworker.domain.Message;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 데이터베이스에 저장하는 SimpleWorker입니다.
 */
@Component
@RequiredArgsConstructor
public class SimpleWorker {
    private static final Logger logger = LoggerFactory.getLogger(SimpleListener.class);

    private final MessageRepository repository;

    public void doSave(final Message message) {
        repository.save(message);
    }

}
