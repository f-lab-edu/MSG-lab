package com.example.msglab.domain;

public enum Status {
    OK(200),
    MOVED_PERMANENTLY(301),
    MOVED_TEMPORARILY(302),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);
    private int value;

    Status(int value) {
        this.value = value;
    }
}
