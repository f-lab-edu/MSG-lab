package com.example.msglab.domain;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Message {
    @Id
    private String id;
    @Column(name = "topic")
    private String to;
    @Valid
    private Notification notification;

    public Message(String to, Notification notification) {
        this.to = to;
        this.notification = notification;
    }

    public void generateID() {
        this.id = UUID.randomUUID().toString();
    }
}
