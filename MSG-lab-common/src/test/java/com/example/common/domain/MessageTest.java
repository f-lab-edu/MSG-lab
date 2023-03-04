package com.example.common.domain;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MessageTest {

    private static InformationalPushMessage getInformationMessage(final String to,
                                                                  final Notification notification,
                                                                  LocalDateTime time) {
        return InformationalPushMessage.builder()
                                       .to(to)
                                       .notification(notification)
                                       .sendingTime(time)
                                       .build();
    }

    @Test
    @DisplayName("광고 메시지 이지만 정보성 메시지로 전송되는 경우, 올바른 메시지가 아닌다.")
    void test5() {
        final Notification notification = new Notification("(광고) 바겐 세일", "본문 수신거부");
        final LocalDateTime timeAlmostAM8 = LocalDateTime.of(2023, 3, 10, 10, 0, 0);
        final InformationalPushMessage message = getInformationMessage("new", notification, timeAlmostAM8);
        Assertions.assertFalse(message.isValidNotification());
    }
}
