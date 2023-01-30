package com.example.common.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RabbitProperty {

    EXCHANGE_NAME("simple.news"),
    QUEUE_NAME("simple.news"),
    ROUTING_KEY("simple.news.#");

    private final String value;
}
