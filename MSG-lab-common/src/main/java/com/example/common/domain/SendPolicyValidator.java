package com.example.common.domain;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SendPolicyValidator {

    public boolean isSendAble(final InformationalPushMessage message) {
        return message.isValidNotification();
    }

    public boolean isSendAble(final PushMessageInfo info, final CommercialPushMessage message) {
        return confirm(info, message);
    }

    private boolean agreedReceive(final PushMessageInfo info) {
        return info.agreedToReceiveCommercialMessages();
    }

    private boolean containUnsubscribe(final Notification notification) {
        return notification.hasUnsubscribeMethodNotificationBody();
    }

    private boolean confirm(final PushMessageInfo info, final CommercialPushMessage message) {
        final Notification notification = message.getNotification();
        return containUnsubscribe(notification) && agreedReceive(info) && message.isSendableTime();
    }
}
