package com.example.msglabapi.adapter.inbound;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.msglabapi.application.PostMessageService;
import com.example.msglabapi.domain.Message;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final PostMessageService service;

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
}
