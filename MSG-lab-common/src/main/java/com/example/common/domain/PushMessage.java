package com.example.common.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
abstract class PushMessage {
    protected String id;

    protected String to;

    protected LocalDateTime sendingTime;

    protected Notification notification;

    public abstract boolean isSendable();

    public abstract boolean isValidNotification();
}
