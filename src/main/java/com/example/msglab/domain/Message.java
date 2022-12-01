package com.example.msglab.domain;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Message {
    private String to;
    @Valid
    private Notification notification;
}
