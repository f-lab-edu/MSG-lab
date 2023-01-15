package com.example.msglab;

import com.example.msglabapi.domain.Message;
import com.example.msglabapi.domain.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PushMessageJsonRequestDummy {

    public static final String MISSING_CURLY_BRACKET = "\n"
                                                       + "  \"to\": \"/topics/news\",\n"
                                                       + "  \"notification\": {\n"
                                                       + "    \"title\": \"Breaking News\",\n"
                                                       + "    \"body\": \"asdad\"\n"
                                                       + "  }\n"
                                                       + "}";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static final String CORRECT_VALUE = toJson(
            new Message("/topics/news", new Notification("Breaking News", "asdad")));
    public static final String EMPTY_TITLE_VALUE = toJson(
            new Message("/topics/news", new Notification("", "asdad")));

    private static <T> String toJson(T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
