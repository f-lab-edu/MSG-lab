package com.example.msglab.adapter;

import com.example.msglab.application.MessageService;
import com.example.msglab.domain.Message;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @ExceptionHandler({HttpMessageConversionException.class, HttpMessageNotReadableException.class, HttpMessageNotWritableException.class})
    public ResponseEntity<String> handle(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * push message를 전송합니다
     *
     * @param message 전송할 메세지
     * @return 전송 이후 리턴하는 문자열
     */
    @PostMapping("/push-message")
    public String sendMsg(@RequestBody @Valid Message message) {
        String id = service.send(message);
        return "sent push-message id : " + id;
    }
}
