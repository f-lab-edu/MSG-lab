package com.example.msglab.adapter;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.msglab.JsonRequestDummy;
import com.example.msglab.application.MessageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class MessageControllerTest {

    @Test
    @DisplayName("정상 데이터에 대해 POST /push-message 요청이 동작하는지 테스트")
    void test1() throws Exception {
        MessageService mock = mock(MessageService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new MessageController(mock)).build();
        mockMvc.perform(post("/push-message").contentType("application/json").content(JsonRequestDummy.correctValue))
            .andExpect(content().string("sent push-message id : null"))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("비정상 데이터에 대해 POST /push-message 요청이 동작하는지 테스트")
    void test2() throws Exception {
        MessageService mock = mock(MessageService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new MessageController(mock)).build();
        mockMvc.perform(post("/push-message").contentType("application/json").content(JsonRequestDummy.NotCorrectValue))
            .andExpect(status().isBadRequest());
    }
}
