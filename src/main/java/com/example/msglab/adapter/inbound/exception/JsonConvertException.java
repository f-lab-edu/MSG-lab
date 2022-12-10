package com.example.msglab.adapter.inbound.exception;

import org.springframework.http.HttpStatus;

import com.example.msglab.adapter.inbound.exception.Constant.ExceptionClass;

public class JsonConvertException extends RuntimeException {
    private final Constant.ExceptionClass exceptionClass;
    private final HttpStatus httpStatus;

    public JsonConvertException(Constant.ExceptionClass exceptionClass, HttpStatus httpStatus, String message) {
        super(exceptionClass.toString() + message);
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public ExceptionClass getExceptionClass() {
        return exceptionClass;
    }

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

    public String getHttpStatusType() {
        return httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
