package com.example.common.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PushMessageInfo {
    private boolean receiveCommercialMessages;

    public boolean agreedToReceiveCommercialMessages() {
        return receiveCommercialMessages;
    }
}
