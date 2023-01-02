package com.example.rabbitmqworker.adapter.inbound;

import com.example.rabbitmqworker.application.SimpleWorker;
import com.example.rabbitmqworker.config.RabbitProperty.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 메세지 브로커에서 메세지를 가져와서 처리합니다.
 */
@Component
@RequiredArgsConstructor
public class SimpleListener {

    private final SimpleWorker worker;

    @RabbitListener(queues = Constants.QUEUE_NAME)
    public void listen(@RequestBody final MessageRequestV1 messageRequestV1) {
        worker.doSave(messageRequestV1.toMessage());
    }
}