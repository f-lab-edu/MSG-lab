package com.example.rabbitmqworker.adapter.inbound;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.rabbitmqworker.application.SavePushMessageService;

import lombok.RequiredArgsConstructor;

/**
 * 메세지 브로커에서 메세지를 가져와서 처리합니다.
 */
@Component
@RequiredArgsConstructor
public class PushMessageListener {

    private final SavePushMessageService service;

    @RabbitListener(queues = "simple.news")
    public void listen(@RequestBody final MessageRequestV1 messageRequestV1) {
        service.save(messageRequestV1.toMessage());
    }
}
