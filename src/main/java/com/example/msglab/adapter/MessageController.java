package com.example.msglab.adapter;

import com.example.msglab.application.MessageService;
import com.example.msglab.domain.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;
    @PostMapping("/send")
    public String sendMsg(@RequestBody Message message) {
        String id = service.send(message);
        return "sent message id : "+id;
    }
}
