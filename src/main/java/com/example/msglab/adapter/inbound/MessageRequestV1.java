package com.example.msglab.adapter.inbound;

import java.util.Map;

import javax.validation.constraints.NotEmpty;

import com.example.msglab.domain.Message;
import com.example.msglab.domain.Notification;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * push-message 전송을 위해 client에게 받는 폼입니다.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MessageRequestV1 {
    private String to;

    @NotEmpty
    private String notificationTitle;

    private String notificationBody;

    @JsonProperty("notification")
    private void unpackNameFromNestedObject(Map<String, String> notification) {
        notificationTitle = notification.get("title");
        notificationBody = notification.get("body");
    }

    public Message toMessage() {
        return new Message(to, new Notification(notificationTitle, notificationBody));
    }
}
