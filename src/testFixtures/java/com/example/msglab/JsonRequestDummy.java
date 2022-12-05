package com.example.msglab;

import com.example.msglab.domain.Message;
import com.example.msglab.domain.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonRequestDummy {

    public static final String correctValue = toJson(new Message("/topics/news", new Notification("Breaking News", "asdad")));

    public static final String NotCorrectValue = toJson(new Message("/topics/news", new Notification("", "asdad")));

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static <T> String toJson(T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
