package com.example.msglab.adapter.inbound;

import com.example.msglab.application.MessageService;
import com.example.msglab.domain.Message;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * push message 전송하는 컨트롤러입니다.
 */
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;

    /**
     * 전송할 메세지 폼을 받아서 push-message 전송
     *
     * @param messageRequestV1 전송할 메세지
     * @return 저장된 메세지
     */
    @PostMapping("/push-message")
    public MessageResponseV1 sendPushMessage(@RequestBody @Valid MessageRequestV1 messageRequestV1) {
        final Message message = messageRequestV1.toMessage();
        final Message send = service.send(message);
        return new MessageResponseV1(send.getId(), send.getTo(), send.getNotification());
    }
}
