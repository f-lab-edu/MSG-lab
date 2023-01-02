package com.example.rabbitmqworker.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Message {

    @Id
    private String id;

    @Column(name = "topic")
    private String to;

    private Notification notification;


    public Message(String to, Notification notification) {
        this.to = to;
        this.notification = notification;
    }

    public Message(String id, String to, Notification notification) {
        this.id = id;
        this.to = to;
        this.notification = notification;
    }
}
