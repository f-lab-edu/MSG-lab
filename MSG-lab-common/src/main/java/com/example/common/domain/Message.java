package com.example.common.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String id;

    private String to;

    private Notification notification;

    private MessageType type;

    private LocalDateTime time;

    public Message(String to, Notification notification) {
        id = UUID.randomUUID().toString();
        this.to = to;
        this.notification = notification;
    }

    public boolean isSendAble() {
        if (!isCommercial()) {
            return true;
        }

        notification.putPrefixIfAbsent();
        return availableTime(time.getHour(), time.getMinute(), time.getSecond());
    }

    boolean isCommercial() {
        return MessageType.COMMERCIAL == type;
    }

    boolean availableTime(int hour, int minute, int second) {
        if (require(hour < 8) || require(hour > 21)) {
            return false;
        }
        if (require(hour == 8)) {
            return require(minute > 0);
        }
        if (require(hour == 21)) {
            return require(second > 0);
        }
        return true;
    }

    boolean require(Boolean condition) {
        return !Boolean.FALSE.equals(condition);
    }
}
