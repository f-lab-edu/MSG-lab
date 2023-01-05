package com.example.rabbitmqworker.adapter.config;

import lombok.Getter;
import lombok.experimental.FieldNameConstants;

@Getter
@FieldNameConstants(onlyExplicitlyIncluded = true)
public enum RabbitProperty {
    @FieldNameConstants.Include EXCHANGE_NAME("simple.topic"),
    @FieldNameConstants.Include QUEUE_NAME("simple.news.breaking"),
    @FieldNameConstants.Include ROUTING_KEY("simple.news.#");

    private final String value;

    RabbitProperty(String value) {
        this.value = value;
    }
}
