package com.example.common.domain;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MessageTest {

    @Test
    @DisplayName("정보성 메시지라면 항상 isSendAble 메소드의 리턴값이 True")
    void test1() {
        final Notification notification = new Notification("속보 태풍 접근", "본문");
        final Message message = Message.builder()
                                       .to("news")
                                       .notification(notification)
                                       .type(MessageType.INFORMATION)
                                       .build();
        Assertions.assertTrue(message.isSendAble());
    }

    @Test
    @DisplayName("광고성 메시지의 (광고) 접두어를 없으면 넣어주는지 테스트")
    void test2() {
        final Notification notification = new Notification("바겐 세일", "본문");
        final LocalDateTime timeAM8 = LocalDateTime.of(2023, 2, 19, 8, 1, 0);
        final Message message = Message.builder()
                                       .to("shop")
                                       .notification(notification)
                                       .type(MessageType.COMMERCIAL)
                                       .time(timeAM8)
                                       .build();
        message.isSendAble();
        Assertions.assertTrue(message.getNotification().getTitle().startsWith("(광고)"));
    }

    @Test
    @DisplayName("광고성 메시지라면 08:01:00 ~ 21:00:01 사이에는 isSendAble 메소드의 리턴값이 True")
    void test3() {
        final Notification notification = new Notification("(광고) 바겐 세일", "본문");
        final LocalDateTime timeAM8 = LocalDateTime.of(2023, 2, 19, 8, 1, 0);
        final LocalDateTime timePM9 = LocalDateTime.of(2023, 2, 19, 21, 0, 1);
        final LocalDateTime timePM3 = LocalDateTime.of(2023, 2, 19, 15, 0, 0);
        final Message messageAtAM8 = Message.builder()
                                            .to("shop")
                                            .notification(notification)
                                            .type(MessageType.COMMERCIAL)
                                            .time(timeAM8)
                                            .build();
        final Message messageAtPM9 = Message.builder()
                                            .to("shop")
                                            .notification(notification)
                                            .type(MessageType.COMMERCIAL)
                                            .time(timePM9)
                                            .build();
        final Message messageAtPM3 = Message.builder()
                                            .to("shop")
                                            .notification(notification)
                                            .type(MessageType.COMMERCIAL)
                                            .time(timePM3)
                                            .build();
        Assertions.assertTrue(messageAtAM8.isSendAble());
        Assertions.assertTrue(messageAtPM9.isSendAble());
        Assertions.assertTrue(messageAtPM3.isSendAble());
    }

    @Test
    @DisplayName("광고성 메시지라면 21:00:00 ~ 08:00:59 사이에는 isSendAble 메소드의 리턴값이 False")
    void test4() {
        final Notification notification = new Notification("(광고) 바겐 세일", "본문");
        final LocalDateTime timeAlmostAM8 = LocalDateTime.of(2023, 2, 19, 8, 0, 59);
        final LocalDateTime timeAlmostPM9 = LocalDateTime.of(2023, 2, 19, 21, 0, 0);
        final LocalDateTime timeAlmostPM11 = LocalDateTime.of(2023, 2, 19, 23, 0, 0);
        final Message messageAlmostAtAM8 = Message.builder()
                                                  .to("shop")
                                                  .notification(notification)
                                                  .type(MessageType.COMMERCIAL)
                                                  .time(timeAlmostAM8)
                                                  .build();
        final Message messageAlmostAtPM9 = Message.builder()
                                                  .to("shop")
                                                  .notification(notification)
                                                  .type(MessageType.COMMERCIAL)
                                                  .time(timeAlmostPM9)
                                                  .build();
        final Message messageAlmostAtPM11 = Message.builder()
                                                   .to("shop")
                                                   .notification(notification)
                                                   .type(MessageType.COMMERCIAL)
                                                   .time(timeAlmostPM11)
                                                   .build();
        Assertions.assertFalse(messageAlmostAtAM8.isSendAble());
        Assertions.assertFalse(messageAlmostAtPM9.isSendAble());
        Assertions.assertFalse(messageAlmostAtPM11.isSendAble());
    }
}
