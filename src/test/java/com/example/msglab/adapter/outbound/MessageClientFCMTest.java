package com.example.msglab.adapter.outbound;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.msglab.MessageDummy;
import com.example.msglab.adapter.config.ConfigFCM;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

class MessageClientFCMTest {

    @Test
    @DisplayName("정상적인 입력이 들어왔을때, MessageClientFCM의 send 메소드가 호출되면 WebClient의 postForEntity가 호출되는지 테스트")
    void test1() {
        ConfigFCM config = mock(ConfigFCM.class);
        RestTemplateBuilder restTemplateBuilder = mock(RestTemplateBuilder.class);
        RestTemplate restTemplate = mock(RestTemplate.class);

        Mockito.when(config.getUrl()).thenReturn("aaa.com");
        Mockito.when(restTemplateBuilder.build()).thenReturn(restTemplate);

        MessageClientFCM messageClientFCM = new MessageClientFCM(config, restTemplateBuilder);
        messageClientFCM.init();

        messageClientFCM.send(MessageDummy.message);
        verify(restTemplateBuilder, times(1)).build();
    }
}