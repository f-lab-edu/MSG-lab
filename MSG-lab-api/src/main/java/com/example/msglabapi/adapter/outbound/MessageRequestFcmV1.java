package com.example.msglabapi.adapter.outbound;

import com.example.msglabapi.domain.Message;

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

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    private static class Notification {
        private String title;
        private String body;
    }
}
