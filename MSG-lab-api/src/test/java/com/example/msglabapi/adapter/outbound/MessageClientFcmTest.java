package com.example.msglabapi.adapter.outbound;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.msglab.MessageDummy;

import feign.FeignException.FeignClientException;
import feign.Request;
import feign.Request.HttpMethod;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@EnableTransactionManagement(proxyTargetClass = true)
//@EnableRetry(proxyTargetClass = true)
@EnableRetry
@EnableFeignClients
class MessageClientFcmTest {

    private static final FcmFeignClient mockFcmFeignClient = mock(FcmFeignClient.class);
    @Autowired
    private MessageClientFcm fcm;

    @BeforeEach
    void init() {
        reset(mockFcmFeignClient);
    }

    @Test
    @DisplayName("정상적인 입력이 들어왔을때, MessageClientFCM의 send 메소드가 호출되면 WebClient의 postForEntity가 호출되는지 테스트")
    void test1() {
        fcm.send(MessageDummy.message);
        verify(mockFcmFeignClient, times(1)).send(any());
    }

    @Test
    @DisplayName("FCM 호출이 실패하면 재요청하는지 테스트")
    void test2() {
        Request request = Request.create(HttpMethod.POST, "a", Collections.EMPTY_MAP, null, null, null);
        doThrow(new FeignClientException(404, "Not Found", request, null, null)).when(mockFcmFeignClient).send(
                any());
        fcm.send(MessageDummy.message);
        verify(mockFcmFeignClient, times(3)).send(any());
    }

    @Configuration
    @EnableRetry
    @EnableFeignClients
    public static class SpringConfig {

        @Bean
        public MessageClientFcm messageClientFcm() {
            return new MessageClientFcm(mockFcmFeignClient);
        }
    }
}