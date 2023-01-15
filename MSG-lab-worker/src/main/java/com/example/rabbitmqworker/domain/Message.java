package com.example.rabbitmqworker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 푸시 메세지입니다.
 * 전송할 내용과 전송할 주제를 담고 있습니다.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Message {

    @Id
    private String id;

    // to는 MySQL 예약어입니다. to 대신 다른 단어를 사용해야합니다.
    @Column(name = "topic")
    private String to;

    private Notification notification;
}
