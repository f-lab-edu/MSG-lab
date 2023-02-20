package com.example.msglabapi.adapter.inbound.version2;

import com.example.msglabapi.domain.Notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class MessageResponseV2 {
    private String id;
    private String to;
    private Notification notification;
    private String type;
}
