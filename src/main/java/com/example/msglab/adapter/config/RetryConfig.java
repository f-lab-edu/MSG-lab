package com.example.msglab.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableRetry
public class RetryConfig {
    @Bean
    public RestTemplate retryableRestTemplate() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setReadTimeout(2000);
        clientHttpRequestFactory.setConnectTimeout(500);

        return new RestTemplate(clientHttpRequestFactory) {
            @Override
            @Retryable(value = HttpServerErrorException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
            public <T> ResponseEntity<T> postForEntity(String url, Object request,
                Class<T> responseType, Object... uriVariables) throws HttpServerErrorException {
                return super.postForEntity(url, request, responseType, uriVariables);
            }

            @Recover
            public <T> ResponseEntity<String> postForEntityRecover(HttpServerErrorException e) {
                return ResponseEntity.internalServerError().body("server error");
            }
        };
    }
}
