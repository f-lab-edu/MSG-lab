package com.example.msglab.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;

import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(name = "topic")
    private String to;
    @Valid
    private Notification notification;


    public Message(String to, Notification notification) {
        this.to = to;
        this.notification = notification;
    }
}
