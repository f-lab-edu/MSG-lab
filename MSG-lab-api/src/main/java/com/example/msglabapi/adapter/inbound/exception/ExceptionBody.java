package com.example.msglabapi.adapter.inbound.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionBody {
    private final String errorType;
    private final String code;
    private final String message;
}
