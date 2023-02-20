package com.example.msglabapi.adapter.inbound.version1;

import javax.validation.constraints.NotEmpty;

import com.example.msglabapi.domain.Message;
import com.example.msglabapi.domain.Notification;
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
    private void unpackNameFromNestedObject(Notification notification) {
        notificationTitle = notification.getTitle();
        notificationBody = notification.getBody();
    }

    public Message toMessage() {
        return new Message(to, new Notification(notificationTitle, notificationBody));
    }
}
