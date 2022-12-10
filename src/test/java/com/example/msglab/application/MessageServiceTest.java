package com.example.msglab.application;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.msglab.MessageDummy;
import com.example.msglab.adapter.outbound.MessageRepository;
import com.example.msglab.domain.MessageClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MessageServiceTest {

    @Test
    @DisplayName("MessageService의 send 메소드가 MessageClient의 send 메소드를 호출하는지 테스트")
    void test1() {
        MessageClient messageClient = mock(MessageClient.class);
        MessageRepository repository = mock(MessageRepository.class);
        MessageService messageService = new MessageService(messageClient, repository);
        messageService.send(MessageDummy.message);
        verify(messageClient, times(1)).send(MessageDummy.message);
    }
}
