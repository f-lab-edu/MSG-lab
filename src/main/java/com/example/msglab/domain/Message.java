package com.example.msglab.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * FCM으로 전송할 push-message입니다.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Message {

    private String id;

    private String to;

    private Notification notification;

    public Message(String to, Notification notification) {
        id = UUID.randomUUID().toString();
        this.to = to;
        this.notification = notification;
    }
}
