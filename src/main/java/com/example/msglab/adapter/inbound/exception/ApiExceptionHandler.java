package com.example.msglab.adapter.inbound.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = { HttpMessageConversionException.class, HttpMessageNotReadableException.class, HttpMessageNotWritableException.class})
    public ResponseEntity<ExceptionBody> handleException(RuntimeException e, HttpServletRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ExceptionBody exceptionBody = new ExceptionBody(httpStatus.getReasonPhrase(), "400", e.getMessage());
        return new ResponseEntity<>(exceptionBody, responseHeaders, httpStatus);
    }
}
