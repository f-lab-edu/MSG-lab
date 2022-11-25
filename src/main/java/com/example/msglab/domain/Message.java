package com.example.msglab.domain;

public class Message {
    private final String to;
    private final Notification notification;

    public Message(String to, Notification notification) {
        this.to = to;
        this.notification = notification;
    }
}
