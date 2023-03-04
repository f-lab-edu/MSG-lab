package com.example.common.domain;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Notification {
    private static final PushMessageResource resource = new PushMessageResource();
    @NotEmpty
    private String title;
    private String body;

    public boolean hasCommercialPrefix() {
        return title.startsWith(resource.getCommercialPrefix());
    }

    public boolean hasUnsubscribeMethodNotificationBody() {
        return body.endsWith(resource.getUnsubscribeMethod());
    }
}
