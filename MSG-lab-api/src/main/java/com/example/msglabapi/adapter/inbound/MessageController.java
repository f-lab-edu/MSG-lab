package com.example.msglabapi.adapter.inbound;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.msglabapi.adapter.inbound.version1.MessageRequestV1;
import com.example.msglabapi.adapter.inbound.version1.MessageResponseV1;
import com.example.msglabapi.adapter.inbound.version2.MessageRequestV2;
import com.example.msglabapi.adapter.inbound.version2.MessageResponseV2;
import com.example.msglabapi.application.PostMessageService;
import com.example.msglabapi.domain.Message;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final PostMessageService service;

    /**
     * Version 1 api
     */
    @PostMapping("/push-message")
    public EntityModel<MessageResponseV1> sendPushMessage(
            @RequestBody @Valid MessageRequestV1 messageRequestV1) {
        final Message message = messageRequestV1.toMessage();
        service.send(message);
        final MessageResponseV1 response = new MessageResponseV1(message.getId(), message.getTo(),
                                                                 message.getNotification());
        return EntityModel.of(
                response,
                linkTo(methodOn(MessageController.class).sendPushMessage(messageRequestV1))
                        .withSelfRel()
        );
    }

    @PostMapping("/v2/push-message")
    public MessageResponseV2 sendPushMessageV2(@RequestBody @Valid MessageRequestV2 request) {
        final Message message = request.toMessage();
        // todo(BueVonHun): PostMessageService#send() 메소드에 리턴값을 추가하면 좋을것 같습니다.
        //  다만, 현재는 메시지 브로커에 데이터를 전달하고 바로 돌아오기 때문에 어떤 결과를 리턴해야 좋을지 고민입니다.
        //  전송 요청이 성공했다는 응답을 내려주는것을 고려하고 잇습니다.
        service.send(message);
        return new MessageResponseV2(message.getId(), message.getTo(), message.getNotification(), "type");
    }
}
