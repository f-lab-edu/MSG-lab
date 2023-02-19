package com.example.common.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotificationTest {

    @Test
    @DisplayName("접두어가 존재하지 않으면 넣어주는지 테스트")
    void test1() {
        final Notification notification = new Notification("특가 세일", "본문");
        notification.putPrefixIfAbsent();
        Assertions.assertTrue(notification.hasPrefix());
    }
}
