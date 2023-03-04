package com.example.common.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class CommercialPushMessage extends PushMessage {

    @Override
    public boolean isValidNotification() {
        return isCommercialNotification();
    }

    private boolean isCommercialNotification() {
        return notification.hasCommercialPrefix();
    }

    public boolean isSendableTime() {
        return isAfterAM8(sendingTime) && isBeforPM9(sendingTime);
    }

    private boolean isBeforPM9(final LocalDateTime dateTime) {
        final LocalTime time = dateTime.toLocalTime();
        return time.isBefore(LocalTime.of(21, 0, 0));
    }

    private boolean isAfterAM8(final LocalDateTime dateTime) {
        final LocalTime time = dateTime.toLocalTime();
        return time.isAfter(LocalTime.of(8, 0, 59));
    }
}
