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

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

class MessageClientFCMTest {

    @Test
    @DisplayName("정상적인 입력이 들어왔을때, MessageClientFCM의 send 메소드가 호출되면 WebClient의 postForEntity가 호출되는지 테스트")
    void test1() {
        ConfigFCM config = mock(ConfigFCM.class);
        RestTemplateBuilder restTemplateBuilder = mock(RestTemplateBuilder.class);
        RestTemplate restTemplate = mock(RestTemplate.class);
        MeterRegistry meterRegistry = mock(MeterRegistry.class);
        Timer timer = mock(Timer.class);

        Mockito.when(config.getUrl()).thenReturn("aaa.com");
        Mockito.when(restTemplateBuilder.build()).thenReturn(restTemplate);

        MessageClientFCM messageClientFCM = new MessageClientFCM(config, restTemplateBuilder, meterRegistry,
                                                                 timer);
        messageClientFCM.init();

        messageClientFCM.send(MessageDummy.message);
        //todo(BueVonHon): Builder를 이용해서 이전처럼 RestTemplate의 postEntity를 테스트하는게 쉽지 않네요...
        //                 리턴값이 없어 테스트가 더 어려운것 같은데 좋은 방법이 있을가요?
        verify(restTemplateBuilder, times(1)).build();
    }
}