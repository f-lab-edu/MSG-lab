package com.example.msglab.adapter.outbound;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.example.msglab.adapter.config.RabbitProperty;
import com.example.msglab.application.outbound.MessageBrokerClient;
import com.example.msglab.domain.Message;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MessageBrokerClientRabbitMQ implements MessageBrokerClient {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(Message message) {
        rabbitTemplate.convertAndSend(RabbitProperty.EXCHANGE_NAME.getValue(),
                                      RabbitProperty.ROUTING_KEY.getValue(), message);
    }
}
