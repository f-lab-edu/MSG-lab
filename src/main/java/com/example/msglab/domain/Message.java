package com.example.msglab.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    private final String to;
    private final Notification notification;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Message(@JsonProperty("to") String to, @JsonProperty("notification") Notification notification) {
        this.to = to;
        this.notification = notification;
    }
}
