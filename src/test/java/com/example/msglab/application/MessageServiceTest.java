package com.example.msglab.application;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.msglab.MessageDummy;
import com.example.msglab.domain.MessageClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MessageServiceTest {

    @Test
    @DisplayName("MessageService의 send 메소드가 MessageClient의 send 메소드를 호출하는지 테스트")
    void test1() {
        MessageClient mock = mock(MessageClient.class);
        MessageService messageService = new MessageService(mock);
        messageService.send(MessageDummy.message);
        verify(mock, times(1)).send(MessageDummy.message);
    }
}
