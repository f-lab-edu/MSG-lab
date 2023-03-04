package com.example.common.domain;

import java.time.LocalTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class CommercialPushMessage extends PushMessage {
    private boolean isBeforPM9(final LocalTime time) {
        return time.isBefore(LocalTime.of(21, 0, 0));
    }

    private boolean isAfterAM8(final LocalTime time) {
        return time.isAfter(LocalTime.of(8, 0, 59));
    }

    @Override
    public boolean isSendable() {
        notification.putPrefixIfAbsent();
        return isSendableTime(sendingTime.toLocalTime());
    }

    boolean isSendableTime(final LocalTime time) {
        return isAfterAM8(time) && isBeforPM9(time);
    }
}
