package com.example.common.domain;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MessageTest {

    private static InformationalPushMessage getInformationMessage(final String to,
                                                                  final Notification notification) {
        return InformationalPushMessage.builder()
                                       .to(to)
                                       .notification(notification)
                                       .build();
    }

    private static InformationalPushMessage getInformationMessage(final String to,
                                                                  final Notification notification,
                                                                  LocalDateTime time) {
        return InformationalPushMessage.builder()
                                       .to(to)
                                       .notification(notification)
                                       .sendingTime(time)
                                       .build();
    }

    private static CommercialPushMessage getCommerceMessage(final String to,
                                                            final Notification notification,
                                                            final LocalDateTime time) {
        return CommercialPushMessage.builder()
                                    .to(to)
                                    .notification(notification)
                                    .sendingTime(time)
                                    .build();
    }

    @Test
    @DisplayName("정보성 메시지라면 항상 isSendAble 메소드의 리턴값이 True")
    void test1() {
        final Notification notification = new Notification("속보 태풍 접근", "본문");
        final InformationalPushMessage message = getInformationMessage("news", notification);
        Assertions.assertTrue(message.isSendable());
    }

    @Test
    @DisplayName("광고성 메시지의 (광고) 접두어를 없으면 넣어주는지 테스트")
    void test2() {
        final Notification notification = new Notification("바겐 세일", "본문");
        final LocalDateTime timeAM8 = LocalDateTime.of(2023, 2, 19, 8, 1, 0);
        final CommercialPushMessage message = getCommerceMessage("shop", notification, timeAM8);
        message.isSendable();
        Assertions.assertTrue(message.getNotification().getTitle().startsWith("(광고)"));
    }

    @Test
    @DisplayName("광고성 메시지라면 08:01:00 ~ 20:59:59 사이에는 isSendAble 메소드의 리턴값이 True")
    void test3() {
        final Notification notification = new Notification("(광고) 바겐 세일", "본문");
        final LocalDateTime timeAM8 = LocalDateTime.of(2023, 2, 19, 8, 1, 0);
        final LocalDateTime timePM9 = LocalDateTime.of(2023, 2, 19, 20, 59, 59);
        final LocalDateTime timePM3 = LocalDateTime.of(2023, 2, 19, 15, 0, 0);
        final CommercialPushMessage messageAtAM8 = getCommerceMessage("shop", notification, timeAM8);
        final CommercialPushMessage messageAtPM9 = getCommerceMessage("shop", notification, timePM9);
        final CommercialPushMessage messageAtPM3 = getCommerceMessage("shop", notification, timePM3);
        Assertions.assertTrue(messageAtAM8.isSendable());
        Assertions.assertTrue(messageAtPM9.isSendable());
        Assertions.assertTrue(messageAtPM3.isSendable());
    }

    @Test
    @DisplayName("광고성 메시지라면 21:00:00 ~ 08:00:59 사이에는 isSendAble 메소드의 리턴값이 False")
    void test4() {
        final Notification notification = new Notification("(광고) 바겐 세일", "본문");
        final LocalDateTime timeAlmostAM8 = LocalDateTime.of(2023, 2, 19, 8, 0, 59);
        final LocalDateTime timeAlmostPM9 = LocalDateTime.of(2023, 2, 19, 21, 0, 0);
        final LocalDateTime timeAlmostPM11 = LocalDateTime.of(2023, 2, 19, 23, 0, 0);
        final CommercialPushMessage messageAlmostAtAM8 = getCommerceMessage("shop", notification,
                                                                            timeAlmostAM8);
        final CommercialPushMessage messageAlmostAtPM9 = getCommerceMessage("shop", notification,
                                                                            timeAlmostPM9);
        final CommercialPushMessage messageAlmostAtPM11 = getCommerceMessage("shop", notification,
                                                                             timeAlmostPM11);
        Assertions.assertFalse(messageAlmostAtAM8.isSendable());
        Assertions.assertFalse(messageAlmostAtPM9.isSendable());
        Assertions.assertFalse(messageAlmostAtPM11.isSendable());
    }

    @Test
    @DisplayName("광고 메시지 이지만 정보성 메시지로 전송되는 경우, 올바른 메시지가 아닌다.")
    void test5() {
        final Notification notification = new Notification("(광고) 바겐 세일", "본문");
        final LocalDateTime timeAlmostAM8 = LocalDateTime.of(2023, 3, 10, 10, 0, 0);
        final InformationalPushMessage message = getInformationMessage("new", notification, timeAlmostAM8);
        Assertions.assertFalse(message.isValidNotification());
    }
}
