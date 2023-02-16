package com.example.msglabapi.adapter.outbound;

import com.example.msglabapi.domain.Message;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * FCM의 api 버전1에 요청으로 보내는 클래스
 */
@AllArgsConstructor
@Getter
public class MessageRequestFcmV1 {

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

    private String to;
    private Notification notification;

    @AllArgsConstructor
    @Getter
    private static class Notification {
        private String title;
        private String body;
    }
}
