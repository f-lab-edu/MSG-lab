package com.example.msglab.application;

import com.example.msglab.domain.Message;
import com.example.msglab.domain.MessageClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final Logger logger = LoggerFactory.getLogger(MessageService.class);
    private final ObjectMapper mapper = new ObjectMapper();

    private final MessageClient messageClient;
    public void send(Message message) throws JsonProcessingException {
        String data = mapper.writeValueAsString(message);
        messageClient.send(data);
    }
}
