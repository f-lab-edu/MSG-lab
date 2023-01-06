package com.example.msglab.adapter.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Configuration
public class MeterConfig {

    @Bean
    public Timer configureMeter(MeterRegistry meterRegistry) {
        return Timer.builder("post push-message")
                              .publishPercentiles(0.5, 0.95)
                              .publishPercentileHistogram()
                              .serviceLevelObjectives(Duration.ofMillis(100))
                              .minimumExpectedValue(Duration.ofMillis(1))
                              .maximumExpectedValue(Duration.ofSeconds(10))
                              .register(meterRegistry);
    }
}
