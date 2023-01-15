package com.example.rabbitmqworker.adapter.inbound;

import com.example.rabbitmqworker.domain.Message;
import com.example.rabbitmqworker.domain.Notification;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MessageRequestV1 {

    private String id;

    private String to;

    private String notificationTitle;

    private String notificationBody;

    @JsonProperty("notification")
    private void unpackNameFromNestedObject(Notification notification) {
        notificationTitle = notification.getTitle();
        notificationBody = notification.getBody();
    }

    public Message toMessage() {
        return new Message(id, to, new Notification(notificationTitle, notificationBody));
    }
}
