package com.example.msglab.application;

import com.example.msglab.MessageDummy;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MessageServiceTest {

    @Test
    void test1() throws JsonProcessingException {
        MessageService messageService = new MessageService();
        String id = messageService.send(MessageDummy.message);
        Assertions.assertEquals("-1", id);
    }
}
