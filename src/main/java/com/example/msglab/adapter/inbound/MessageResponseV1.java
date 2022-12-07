package com.example.msglab.adapter.inbound;

import org.springframework.http.HttpStatus;

import com.example.msglab.adapter.inbound.Constant.ExceptionClass;
import com.example.msglab.domain.Message;
import com.example.msglab.domain.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * POST /push-message의 응답
 * 저장된 푸시 메세지에 대한 정보를 담고 있다.
 */
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseV1 {
    private String id;
    private String to;
    private Notification notification;

    public static String toJson(Message message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new ConvertException(ExceptionClass.PUSH, HttpStatus.BAD_REQUEST,
                                       "can't convert message to json");
        }
    }
}
