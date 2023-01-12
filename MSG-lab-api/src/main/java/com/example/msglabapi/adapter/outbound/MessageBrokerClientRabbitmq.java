package com.example.msglabapi.adapter.outbound;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.example.msglabapi.adapter.config.RabbitProperty;
import com.example.msglabapi.application.outbound.MessageBrokerClient;
import com.example.msglabapi.domain.Message;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MessageBrokerClientRabbitmq implements MessageBrokerClient {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(Message message) {
        rabbitTemplate.convertAndSend(RabbitProperty.EXCHANGE_NAME.getValue(),
                                      RabbitProperty.ROUTING_KEY.getValue(), message);
    }
}
