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
    @DisplayName("POST /send가 요청되면 MessageController에서 올바른 응답을 내려주는지 테스트")
    void test1() throws Exception {
        MessageService mock = mock(MessageService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new MessageController(mock)).build();
        mockMvc.perform(post("/send").contentType("application/json").content(JsonRequestDummy.value))
            .andExpect(content().string("sent message"))
            .andExpect(status().isOk());
    }
}
