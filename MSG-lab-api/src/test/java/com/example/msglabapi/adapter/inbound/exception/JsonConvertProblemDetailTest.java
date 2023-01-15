package com.example.msglabapi.adapter.inbound.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JsonConvertProblemDetailTest {

    @Test
    @DisplayName("JsonConvertProblemDetail 클래스의 getDetail 메소드 테스트")
    void test1() {
        final JsonConvertProblemDetail problemDetail = new JsonConvertProblemDetail();
        Assertions.assertEquals("json parsing error Please check the format again", problemDetail.getDetail());
    }
}
