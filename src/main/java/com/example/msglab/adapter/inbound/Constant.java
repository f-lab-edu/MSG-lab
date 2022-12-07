package com.example.msglab.adapter.inbound;

public class Constant {
    public enum ExceptionClass {
        PUSH("push-message");

        private final String exceptionClassName;

        ExceptionClass(String exceptionClassName) {
            this.exceptionClassName = exceptionClassName;
        }

        public String getExceptionClass() {
            return exceptionClassName;
        }

        @Override
        public String toString() {
            return getExceptionClass() + " Exception. ";
        }
    }
}
