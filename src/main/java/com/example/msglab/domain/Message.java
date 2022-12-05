package com.example.msglab.domain;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * FCM으로 전송할 push-message입니다.
 */
@NoArgsConstructor
@Getter
@Entity
public class Message {
    @Id
    private final String id = UUID.randomUUID().toString();
    @Column(name = "topic")
    private String to;
    @Valid
    private Notification notification;


    public Message(String to, Notification notification) {
        this.to = to;
        this.notification = notification;
    }
}
