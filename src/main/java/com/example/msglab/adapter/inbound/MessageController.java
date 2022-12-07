package com.example.msglab.adapter.inbound;

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
     * 전송할 메세지 폼을 받아서 push-message 전송
     *
     * @param messageRequestV1 전송할 메세지
     * @return 저장된 메세지
     */
    @PostMapping("/push-message")
    public String sendPushMessage(@RequestBody @Valid MessageRequestV1 messageRequestV1) {
        Message message = messageRequestV1.toMessage();
        Message send = service.send(message);
        return MessageResponseV1.toJson(send);
    }
}
