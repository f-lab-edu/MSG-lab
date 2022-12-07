package com.example.msglab.adapter.inbound;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = ConvertException.class)
    public ResponseEntity<Map<String, String>> handleException(RuntimeException e, HttpServletRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String, String> map = getMap(e, httpStatus);
        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }

    private Map<String, String> getMap(RuntimeException e, HttpStatus httpStatus) {
        return Map.of("error type", httpStatus.getReasonPhrase(), "code", "400", "message",
                                         e.getMessage());
    }
}
