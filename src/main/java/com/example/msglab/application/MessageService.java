package com.example.msglab.application;

import com.example.msglab.adapter.config.RabbitProperty.Constants;
import com.example.msglab.domain.Message;
import com.example.msglab.domain.MessageClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final Logger logger = LoggerFactory.getLogger(MessageService.class);
    private final MessageClient messageClient;

    private final RabbitTemplate rabbitTemplate;

    /**
     * push-message를 전송하고, 데이터베이스에 저장합니다.
     *
     * @param message push-message로 전송할 메세지
     * @return 저장된 메세지
     */
    public void send(final Message message) {
        messageClient.send(message);
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_NAME, Constants.ROUTING_KEY, message);
    }
}
