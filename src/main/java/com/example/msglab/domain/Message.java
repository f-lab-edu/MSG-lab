package com.example.msglab.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Message {
    private String to;
    @Valid
    private Notification notification;
}
