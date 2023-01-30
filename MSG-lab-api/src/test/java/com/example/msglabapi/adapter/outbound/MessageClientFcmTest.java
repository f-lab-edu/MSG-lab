package com.example.msglabapi.adapter.outbound;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.msglab.MessageDummy;
import com.example.msglab.common.FakeRestTemplateBuilder;
import com.example.msglabapi.adapter.config.PropertyFcm;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableRetry(proxyTargetClass = true)
class MessageClientFcmTest {
    private static final FakeRestTemplateBuilder TEMPLATE_BUILDER = new FakeRestTemplateBuilder();

    @Autowired
    private MessageClientFcm fcm;

    @BeforeEach
    void clear() {
        final RestTemplate restTemplate = TEMPLATE_BUILDER.getRestTemplate();
        reset(restTemplate);
    }

    @Test
    @DisplayName("정상적인 입력이 들어왔을때, MessageClientFCM의 send 메소드가 호출되면 WebClient의 postForEntity가 호출되는지 테스트")
    void test1() {
        final RestTemplate restTemplate = TEMPLATE_BUILDER.getRestTemplate();
        fcm.send(MessageDummy.message);
        verify(restTemplate, times(1)).exchange(any(), eq(String.class));
    }

    @Test
    @DisplayName("FCM 호출이 실패하면 재요청하는지 테스트")
    void test2() {
        final RestTemplate restTemplate = TEMPLATE_BUILDER.getRestTemplate();
        final ResponseEntity<String> response = new ResponseEntity<>("body", HttpStatus.OK);
        when(restTemplate.exchange(any(), eq(String.class)))
                .thenThrow(new RestClientException("fail1"))
                .thenThrow(new RestClientException("fail2"))
                .thenReturn(response);
        TEMPLATE_BUILDER.setRestTemplate(restTemplate);
        fcm.send(MessageDummy.message);
        verify(restTemplate, times(3)).exchange(any(), eq(String.class));
    }

    @Test
    @DisplayName("FCM 호출이 실패하고, 3번 재요청도 실패하면 응답이 올바르게 내려오는지 테스트")
    void test3() {
        final RestTemplate restTemplate = TEMPLATE_BUILDER.getRestTemplate();
        when(restTemplate.exchange(any(), eq(String.class)))
                .thenThrow(new RestClientException("fail1"))
                .thenThrow(new RestClientException("fail2"))
                .thenThrow(new RestClientException("fail3"));
        TEMPLATE_BUILDER.setRestTemplate(restTemplate);
        fcm.send(MessageDummy.message);
        verify(restTemplate, times(3)).exchange(any(), eq(String.class));
        // todo(hun): send() 메소드에 리턴값이 추가되면 테스트 코드를 추가해야함.
    }

    @Configuration
    @EnableRetry
    public static class SpringConfig {
        @Bean
        public MessageClientFcm messageClientFcm() {
            final PropertyFcm propertyFcm = mock(PropertyFcm.class);
            final RestTemplate restTemplate = TEMPLATE_BUILDER.build();
            when(propertyFcm.getUrl()).thenReturn("url");
            return new MessageClientFcm(propertyFcm, restTemplate);
        }
    }
}