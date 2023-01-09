package com.example.rabbitmqworker.adapter.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldNameConstants;

@Getter
@FieldNameConstants(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public enum RabbitProperty {
    @FieldNameConstants.Include EXCHANGE_NAME("simple.topic"),
    @FieldNameConstants.Include QUEUE_NAME("simple.news.breaking"),
    @FieldNameConstants.Include ROUTING_KEY("simple.news.#");

    private final String value;
}
