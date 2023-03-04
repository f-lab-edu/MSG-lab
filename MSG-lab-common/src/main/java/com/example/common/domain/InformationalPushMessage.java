package com.example.common.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class InformationalPushMessage extends PushMessage {
    @Override
    public boolean isSendable() {
        return true;
    }

    @Override
    public boolean isValidNotification() {
        return isInformationalNotification();
    }

    private boolean isInformationalNotification() {
        return !notification.hasCommercialPrefix();
    }
}
