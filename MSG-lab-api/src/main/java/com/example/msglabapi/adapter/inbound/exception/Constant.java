package com.example.msglabapi.adapter.inbound.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Constant {
    @AllArgsConstructor
    @Getter
    public enum ExceptionClass {
        PUSH("push-message");

        private final String exceptionClassName;

        @Override
        public String toString() {
            return getExceptionClassName() + " Exception. ";
        }
    }
}
