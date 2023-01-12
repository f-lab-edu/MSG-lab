package com.example.msglab;

import com.example.msglabapi.domain.Message;
import com.example.msglabapi.domain.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonRequestDummy {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static final String correctValue = toJson(
            new Message("/topics/news", new Notification("Breaking News", "asdad")));

    public static final String NotCorrectValue = toJson(
            new Message("/topics/news", new Notification("", "asdad")));

    private static <T> String toJson(T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
