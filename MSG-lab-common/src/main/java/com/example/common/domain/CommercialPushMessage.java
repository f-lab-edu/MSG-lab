package com.example.common.domain;

import java.time.LocalTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class CommercialPushMessage extends PushMessage {
    private static boolean isAfterPM9(final LocalTime time) {
        return time.isAfter(LocalTime.of(20, 59, 59));
    }

    private static boolean isBeforeAM8(final LocalTime time) {
        return time.isBefore(LocalTime.of(8, 1, 0));
    }

    @Override
    public boolean isSendable() {
        notification.putPrefixIfAbsent();
        return isSendableTime(sendingTime.toLocalTime());
    }

    boolean isSendableTime(final LocalTime time) {
        return !isBeforeAM8(time) && !isAfterPM9(time);
    }
}
