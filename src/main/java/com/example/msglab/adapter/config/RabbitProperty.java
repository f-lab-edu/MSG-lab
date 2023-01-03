package com.example.msglab.adapter.config;

public enum RabbitProperty {

    EXCHANGE_NAME("simple.news"), QUEUE_NAME("simple.news"), ROUTING_KEY("simple.news.#");

    private final String value;

    public String getValue() {
        return value;
    }

    RabbitProperty(String value) {
        this.value = value;
    }
    public static final class Constants {
        public static final String EXCHANGE_NAME = "simple.news";
        public static final String QUEUE_NAME = "simple.news";
        public static final String ROUTING_KEY = "simple.news.#";

        private Constants() {
        }
    }
}
