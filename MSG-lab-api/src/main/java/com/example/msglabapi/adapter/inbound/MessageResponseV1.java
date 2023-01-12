package com.example.msglabapi.adapter.inbound;

import com.example.msglabapi.domain.Notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * POST /push-message의 응답
 * 저장된 푸시 메세지에 대한 정보를 담고 있다.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MessageResponseV1 {
    private String id;
    private String to;
    private Notification notification;
}
