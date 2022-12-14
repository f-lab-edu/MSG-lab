package com.example.rabbitmqworker.application;

import org.springframework.stereotype.Service;

import com.example.rabbitmqworker.adapter.outbound.MessageRepository;
import com.example.rabbitmqworker.domain.Message;

import lombok.RequiredArgsConstructor;

/**
 * 데이터베이스에 푸시 메세지를 저장하는 SavePushMessageService
 */
@Service
@RequiredArgsConstructor
public class SavePushMessageService {
    private final MessageRepository repository;

    public void save(final Message message) {
        repository.save(message);
    }
}
