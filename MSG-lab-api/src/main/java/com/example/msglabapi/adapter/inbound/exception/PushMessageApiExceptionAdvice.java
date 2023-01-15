package com.example.msglabapi.adapter.inbound.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 푸시 메세지 도메인의 익셉션을 처리하는 어드바이스입니다.
 */
@RestControllerAdvice
public class PushMessageApiExceptionAdvice {

    @ExceptionHandler(value = HttpMessageConversionException.class)
    public ResponseEntity<JsonConvertProblemDetail> handleHttpMessageConversionException() {
        final JsonConvertProblemDetail detail = new JsonConvertProblemDetail();
        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/problem+json");
        return new ResponseEntity<>(detail, responseHeaders, HttpStatus.BAD_REQUEST);
    }
}
