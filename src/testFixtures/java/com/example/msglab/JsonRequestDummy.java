package com.example.msglab;

import com.example.msglab.domain.Message;
import com.example.msglab.domain.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonRequestDummy {

    public static final String correctValue;

    public static final String NotCorrectValue;

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            correctValue = objectMapper.writeValueAsString(new Message("/topics/news", new Notification("Breaking News", "asdad")));
            Message invalidMessage = new Message("/topics/news", new Notification("", "asdad"));
            NotCorrectValue = objectMapper.writeValueAsString(invalidMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
