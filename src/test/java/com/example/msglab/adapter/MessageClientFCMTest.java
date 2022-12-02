package com.example.msglab.adapter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.msglab.JsonRequestDummy;
import com.example.msglab.MessageDummy;
import com.example.msglab.adapter.config.ConfigFCM;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

class MessageClientFCMTest {

    @Test
    @DisplayName("정상적인 입력이 들어왔을때, MessageClientFCM의 send 메소드가 호출되면 WebClient의 postForEntity가 호출되는지 테스트")
    void test1() {
        ConfigFCM config = mock(ConfigFCM.class);
        WebClinet webClinet = mock(WebClinet.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", config.getAuth());
        HttpEntity<String> httpEntity = new HttpEntity<>(JsonRequestDummy.correctValue, headers);

        MessageClientFCM messageClientFCM = new MessageClientFCM(config, webClinet);
        messageClientFCM.init();

        messageClientFCM.send(MessageDummy.message);
        verify(webClinet, times(1)).postForEntity(null, httpEntity, String.class);
    }
}
