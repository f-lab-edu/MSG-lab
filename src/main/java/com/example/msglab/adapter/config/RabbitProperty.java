package com.example.msglab.adapter.config;

import lombok.Getter;

@Getter
public enum RabbitProperty {

    EXCHANGE_NAME("simple.news"), QUEUE_NAME("simple.news"), ROUTING_KEY("simple.news.#");

    private final String value;

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
