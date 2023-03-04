package com.example.common.domain;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SendPolicyValidatorTest {

    private final SendPolicyValidator validator = new SendPolicyValidator();
    private final PushMessageInfo info = new PushMessageInfo(true);

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
    @DisplayName("정보성 푸시 메시지에 (광고) 접두어가 없으면 전송 가능")
    void test1() {
        final InformationalPushMessage message = InformationalPushMessage.builder()
                                                                         .id(null)
                                                                         .to(null)
                                                                         .sendingTime(null)
                                                                         .notification(
                                                                                 new Notification("속보 태풍 접근",
                                                                                                  "동해안 태풍"))
                                                                         .build();
        Assertions.assertTrue(validator.isSendAble(message));
    }

    @Test
    @DisplayName("정보성 푸시 메시지에 (광고) 접두어가 있으면 전송 불가능")
    void test2() {
        final InformationalPushMessage message = InformationalPushMessage.builder()
                                                                         .id(null)
                                                                         .to(null)
                                                                         .sendingTime(null)
                                                                         .notification(
                                                                                 new Notification(
                                                                                         "(광고) 타임 세일 임박",
                                                                                         "시즌 세일"))
                                                                         .build();
        Assertions.assertFalse(validator.isSendAble(message));
    }

    @Test
    @DisplayName("광고성 메시지라면 08:01:00 ~ 20:59:59 사이에는 isSendAble 메소드의 리턴값이 True")
    void test3() {
        final Notification notification = new Notification("(광고) 바겐 세일", "본문 수신거부");
        final LocalDateTime timeAM8 = LocalDateTime.of(2023, 2, 19, 8, 1, 0);
        final LocalDateTime timePM9 = LocalDateTime.of(2023, 2, 19, 20, 59, 59);
        final LocalDateTime timePM3 = LocalDateTime.of(2023, 2, 19, 15, 0, 0);
        final CommercialPushMessage messageAtAM8 = getCommerceMessage("shop", notification, timeAM8);
        final CommercialPushMessage messageAtPM9 = getCommerceMessage("shop", notification, timePM9);
        final CommercialPushMessage messageAtPM3 = getCommerceMessage("shop", notification, timePM3);

        Assertions.assertTrue(validator.isSendAble(info, messageAtAM8));
        Assertions.assertTrue(validator.isSendAble(info, messageAtPM9));
        Assertions.assertTrue(validator.isSendAble(info, messageAtPM3));
    }

    @Test
    @DisplayName("광고성 메시지라면 21:00:00 ~ 08:00:59 사이에는 isSendAble 메소드의 리턴값이 False")
    void test4() {
        final Notification notification = new Notification("(광고) 바겐 세일", "본문 수신거부");
        final LocalDateTime timeAlmostAM8 = LocalDateTime.of(2023, 2, 19, 8, 0, 59);
        final LocalDateTime timeAlmostPM9 = LocalDateTime.of(2023, 2, 19, 21, 0, 0);
        final LocalDateTime timeAlmostPM11 = LocalDateTime.of(2023, 2, 19, 23, 0, 0);
        final CommercialPushMessage messageAlmostAtAM8 = getCommerceMessage("shop", notification,
                                                                            timeAlmostAM8);
        final CommercialPushMessage messageAlmostAtPM9 = getCommerceMessage("shop", notification,
                                                                            timeAlmostPM9);
        final CommercialPushMessage messageAlmostAtPM11 = getCommerceMessage("shop", notification,
                                                                             timeAlmostPM11);
        Assertions.assertFalse(validator.isSendAble(info, messageAlmostAtAM8));
        Assertions.assertFalse(validator.isSendAble(info, messageAlmostAtPM9));
        Assertions.assertFalse(validator.isSendAble(info, messageAlmostAtPM11));
    }
}