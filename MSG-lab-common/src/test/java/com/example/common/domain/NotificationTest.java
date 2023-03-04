package com.example.common.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotificationTest {

    @Test
    @DisplayName("접두어가 없으면 hasCommercialPrefix 리턴이 false")
    void test1() {
        final Notification notification = new Notification("특가 세일", "본문");
        Assertions.assertFalse(notification.hasCommercialPrefix());
    }

    @Test
    @DisplayName("접두어가 있으면 hasCommercialPrefix 리턴이 true")
    void test2() {
        final Notification notification = new Notification("(광고) 특가 세일", "본문");
        Assertions.assertTrue(notification.hasCommercialPrefix());
    }

    @Test
    void hasUnsubscribeMethodNotificationBody() {
        final Notification notification = new Notification("(광고) 특가 세일", "본문");
        Assertions.assertFalse(notification.hasUnsubscribeMethodNotificationBody());
    }

    @Test
    void hasUnsubscribeMethodNotificationBody2() {
        final Notification notification = new Notification("(광고) 특가 세일", "본문 수신거부");
        Assertions.assertTrue(notification.hasUnsubscribeMethodNotificationBody());
    }
}
