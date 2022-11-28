package com.example.msglab.domain;

public enum MessageURL {
    FCM("https://fcm.googleapis.com/fcm/send");

    private String url;

    MessageURL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
