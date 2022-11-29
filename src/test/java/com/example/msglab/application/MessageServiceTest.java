package com.example.msglab.application;

import com.example.msglab.MessageDummy;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MessageServiceTest {

    @Test
    @DisplayName("메세지 서비스의 send 메소드가 올바르게 동작하는지 테스트")
    void test1() throws JsonProcessingException {
        MessageService messageService = new MessageService();
        // todo(hun): 이후 작업에서 서비스의 내용이 채워지면, 테스트 추가하기
    }
}
