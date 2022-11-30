package com.example.msglab.application;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.msglab.JsonRequestDummy;
import com.example.msglab.MessageDummy;
import com.example.msglab.domain.MessageClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MessageServiceTest {

    @Test
    @DisplayName("메세지 서비스의 send 메소드가 올바르게 동작하는지 테스트")
    void test1() throws JsonProcessingException {
        MessageClient mock = mock(MessageClient.class);
        MessageService messageService = new MessageService(mock);
        messageService.send(MessageDummy.message);
        verify(mock, times(1)).send(JsonRequestDummy.value);
    }
}
