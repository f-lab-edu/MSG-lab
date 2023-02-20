package com.example.msglabapi.adapter.inbound.exception;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.msglab.PushMessageJsonRequestV1Dummy;
import com.example.msglab.PushMessageJsonResponseDummy;
import com.example.msglabapi.adapter.inbound.MessageController;
import com.example.msglabapi.application.PostMessageService;

class PushMessageApiExceptionAdviceTest {

    private final PostMessageService mockService = mock(PostMessageService.class);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new MessageController(mockService))
                                                   .setControllerAdvice(PushMessageApiExceptionAdvice.class)
                                                   .build();

    @Test
    @DisplayName("PushMessageApiExceptionAdvice 클래스의 handleHttpMessageConversionException 메소드 테스트")
    void test1() throws Exception {
        mockMvc.perform(post("/push-message").contentType("application/json")
                                             .content(PushMessageJsonRequestV1Dummy.MISSING_CURLY_BRACKET))
               .andExpect(status().isBadRequest())
               .andExpect(content().string(PushMessageJsonResponseDummy.CONVERSION_EXCEPTION))
               .andExpect(content().contentType("application/problem+json"));
    }
}
