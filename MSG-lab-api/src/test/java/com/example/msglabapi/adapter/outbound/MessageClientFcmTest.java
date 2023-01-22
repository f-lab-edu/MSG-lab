package com.example.msglabapi.adapter.outbound;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.msglab.MessageDummy;
import com.example.msglab.PropertyFcmDummy;
import com.example.msglabapi.adapter.config.PropertyFcm;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableRetry(proxyTargetClass = true)
class MessageClientFcmTest {

    private static final RestTemplate REST_TEMPLATE_SUCCESS = mock(RestTemplate.class);

    private static final RestTemplate REST_TEMPLATE_FAIL = mock(RestTemplate.class);

    private final MessageClientFcm successMessageClientFcm;

    private final MessageClientFcm failMessageClientFcm;

    @Autowired
    private MessageClientFcmTest(@Qualifier("retryThenSuccess") MessageClientFcm successMessageClientFcm,
                                 @Qualifier("retryThenFail") MessageClientFcm failMessageClientFcm) {
        this.successMessageClientFcm = successMessageClientFcm;
        this.failMessageClientFcm = failMessageClientFcm;
    }

    ;

    @Test
    @DisplayName("정상적인 입력이 들어왔을때, MessageClientFCM의 send 메소드가 호출되면 WebClient의 postForEntity가 호출되는지 테스트")
    void test1() {
        final RestTemplateBuilder restTemplateBuilder = mock(RestTemplateBuilder.class);
        final RestTemplate restTemplate = mock(RestTemplate.class);
        final MessageClientFcm messageClientFCM = new MessageClientFcm(PropertyFcmDummy.CORRECT_PROPERTY,
                                                                       restTemplateBuilder);

        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        when(restTemplateBuilder.setReadTimeout(any())).thenReturn(restTemplateBuilder);
        when(restTemplateBuilder.setConnectTimeout(any())).thenReturn(restTemplateBuilder);
        messageClientFCM.init();

        messageClientFCM.send(MessageDummy.message);
        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(String.class));
    }

    @Test
    @DisplayName("FCM 호출이 실패하면 재요청하는지 테스트")
    void test2() {
        successMessageClientFcm.send(MessageDummy.message);
        verify(REST_TEMPLATE_SUCCESS, times(3)).exchange(any(), eq(String.class));
    }

    @Test
    @DisplayName("FCM 호출이 실패하고, 3번 재요청도 실패하면 응답이 올바르게 내려오는지 테스트")
    void test3() {
        failMessageClientFcm.send(MessageDummy.message);
        verify(REST_TEMPLATE_FAIL, times(3)).exchange(any(), eq(String.class));
        // todo(hun): send() 메소드에 리턴값이 추가되면 테스트 코드를 추가해야함.
    }

    @Configuration
    @EnableRetry
    public static class SpringConfig {

        @Bean
        @Qualifier("retryThenSuccess")
        public MessageClientFcm messageClientFcmSuccess() throws Exception {
            final PropertyFcm propertyFcm = mock(PropertyFcm.class);
            final RestTemplateBuilder restTemplateBuilder = mock(RestTemplateBuilder.class);
            final ResponseEntity<String> response = new ResponseEntity<>("body", HttpStatus.ACCEPTED);

            when(restTemplateBuilder.build()).thenReturn(REST_TEMPLATE_SUCCESS);
            when(restTemplateBuilder.setReadTimeout(any())).thenReturn(restTemplateBuilder);
            when(restTemplateBuilder.setConnectTimeout(any())).thenReturn(restTemplateBuilder);
            when(propertyFcm.getUrl()).thenReturn("url");
            when(REST_TEMPLATE_SUCCESS.exchange(any(), eq(String.class)))
                    .thenThrow(new HttpServerErrorException(HttpStatus.REQUEST_TIMEOUT))
                    .thenThrow(new HttpServerErrorException(HttpStatus.REQUEST_TIMEOUT))
                    .thenReturn(response);

            return new MessageClientFcm(propertyFcm, restTemplateBuilder);
        }

        @Bean
        @Qualifier("retryThenFail")
        public MessageClientFcm messageClientFcmFail() throws Exception {
            final PropertyFcm propertyFcm = mock(PropertyFcm.class);
            final RestTemplateBuilder restTemplateBuilder = mock(RestTemplateBuilder.class);

            when(restTemplateBuilder.build()).thenReturn(REST_TEMPLATE_FAIL);
            when(restTemplateBuilder.setReadTimeout(any())).thenReturn(restTemplateBuilder);
            when(restTemplateBuilder.setConnectTimeout(any())).thenReturn(restTemplateBuilder);
            when(propertyFcm.getUrl()).thenReturn("url");
            when(REST_TEMPLATE_FAIL.exchange(any(), eq(String.class)))
                    .thenThrow(new RestClientException("asd"));

            return new MessageClientFcm(propertyFcm, restTemplateBuilder);
        }
    }
}