package com.example.msglab.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Message {
    private final String to;
    private final Notification notification;

    public Message(@JsonProperty("to") String to, @JsonProperty("notification") Notification notification) {
        this.to = to;
        this.notification = notification;
    }
}
