package com.example.common.config;

import lombok.Getter;

@Getter
public enum RabbitProperty {

    EXCHANGE_NAME("simple.news"),
    QUEUE_NAME("simple.news"),
    ROUTING_KEY("simple.news.#");

    private final String value;

    RabbitProperty(String value) {
        this.value = value;
    }
}
