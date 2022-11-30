package com.example.msglab.adapter;

import com.example.msglab.application.MessageService;
import com.example.msglab.domain.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;

    @ExceptionHandler
    public ResponseEntity<String> handle(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/send")
    public String sendMsg(@RequestBody Message message) throws JsonProcessingException {
        service.send(message);
        return "sent message";
    }
}
