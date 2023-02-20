package com.example.msglabapi.adapter.inbound.version2;

import javax.validation.constraints.NotEmpty;

import com.example.msglabapi.domain.Message;
import com.example.msglabapi.domain.Notification;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Version 2 api
 * 아래와 같은 데이터를 유저로부터 전달받습니다.
 * {
 *      "to":"/topics/news",
 *      "type":"information",
 *      "notification":{
 *          "title":"Breaking News",
 *          "body":"summer coming"
 *      }
 * }
 */

@Getter
@Builder
@AllArgsConstructor
public class MessageRequestV2 {
    private String to;
    private String type;

    @NotEmpty
    private String notificationTitle;

    private String notificationBody;

    @JsonProperty("notification")
    private void unpackNameFromNestedObject(Notification notification) {
        notificationTitle = notification.getTitle();
        notificationBody = notification.getBody();
    }

    public Message toMessage() {
        // todo(BueVonhun): #111 PR이 merge 되면 수정해야한다.(현재는 도메인에서 V2 메시지 기능을 서포트 안함)
        return new Message(to, new Notification(notificationTitle, notificationBody));
    }
}
