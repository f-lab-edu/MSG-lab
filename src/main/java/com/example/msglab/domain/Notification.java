package com.example.msglab.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Notification {
    private final String title;
    private final String body;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Notification(@JsonProperty("title") String title, @JsonProperty("body") String body) {
        this.title = title;
        this.body = body;
    }
}
