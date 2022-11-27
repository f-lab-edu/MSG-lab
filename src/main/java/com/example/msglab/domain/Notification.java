package com.example.msglab.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Notification {
    private final String title;
    private final String body;

    public Notification(@JsonProperty("title") String title, @JsonProperty("body") String body) {
        this.title = title;
        this.body = body;
    }
}
