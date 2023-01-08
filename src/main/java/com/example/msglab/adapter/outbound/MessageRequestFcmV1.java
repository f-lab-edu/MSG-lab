package com.example.msglab.adapter.outbound;

import org.springframework.http.HttpStatus;

import com.example.msglab.adapter.inbound.exception.Constant.ExceptionClass;
import com.example.msglab.adapter.inbound.exception.JsonConvertException;
import com.example.msglab.domain.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * FCM의 api 버전1에 요청으로 보내는 클래스
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MessageRequestFcmV1 {

    private String to;
    private Notification notification;
    public static final ObjectMapper mapper = new ObjectMapper();

    /**
     * FCM의 api 버전1 형식에 맞게 메세지를 변환
     *
     * @param message 전송하고 싶은 메세지
     * @return FCM api 버전1에 맞게 변환
     */
    public static MessageRequestFcmV1 from(Message message) {
        return new MessageRequestFcmV1(message.getTo(), new Notification(message.getNotification().getTitle(),
                                                                         message.getNotification().getBody()));
    }

    public String toJson() {
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new JsonConvertException(ExceptionClass.PUSH, HttpStatus.BAD_REQUEST,
                                           "can't convert messageRequestFcm to json");
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private static class Notification {
        private String title;
        private String body;
    }
}
