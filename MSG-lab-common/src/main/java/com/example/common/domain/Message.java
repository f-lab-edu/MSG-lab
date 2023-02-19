package com.example.common.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Message {
    private String id;

    private String to;

    private Notification notification;

    private LocalDateTime time;

    public Message(final String to, final Notification notification) {
        id = UUID.randomUUID().toString();
        this.to = to;
        this.notification = notification;
        time = LocalDateTime.now();
    }

    private static boolean isAfterPM9(final LocalTime time) {
        return time.isAfter(LocalTime.of(20, 59, 59));
    }

    private static boolean isBeforeAM8(final LocalTime time) {
        return time.isBefore(LocalTime.of(8, 1, 0));
    }

    public boolean isSendable() {
        if (notification.isInformation()) {
            return true;
        }

        notification.putPrefixIfAbsent();
        return isSendableTime(time.toLocalTime());
    }

    boolean isSendableTime(final LocalTime time) {
        return !isBeforeAM8(time) && !isAfterPM9(time);
    }
}
