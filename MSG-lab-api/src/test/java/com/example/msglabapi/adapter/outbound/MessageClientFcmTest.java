package com.example.msglabapi.adapter.outbound;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import com.example.msglab.MessageDummy;
import com.example.msglab.PropertyFcmDummy;

class MessageClientFcmTest {

    @Test
    @DisplayName("정상적인 입력이 들어왔을때, MessageClientFCM의 send 메소드가 호출되면 WebClient의 postForEntity가 호출되는지 테스트")
    void test1() {
        final RestTemplate restTemplate = mock(RestTemplate.class);

        final MessageClientFcm messageClientFCM = new MessageClientFcm(PropertyFcmDummy.CORRECT_PROPERTY,
                                                                       restTemplate);
        messageClientFCM.init();

        messageClientFCM.send(MessageDummy.message);
        verify(restTemplate, times(1)).exchange(any(), eq(String.class));
    }
}