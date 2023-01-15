package com.example.msglabapi.adapter.inbound;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.msglab.PushMessageJsonRequestDummy;
import com.example.msglabapi.application.PostMessageService;

class MessageControllerTest {

    private PostMessageService mockService = mock(PostMessageService.class);
    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new MessageController(mockService)).build();

    @Test
    @DisplayName("POST /push-message가 요청되면 MessageController에서 올바른 응답을 내려주는지 테스트")
    void test1() throws Exception {
        mockMvc.perform(post("/push-message").contentType("application/json")
                                             .content(PushMessageJsonRequestDummy.CORRECT_VALUE))
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("비정상 데이터에 대해 POST /push-message 요청이 동작하는지 테스트")
    void test2() throws Exception {
        mockMvc.perform(post("/push-message").contentType("application/json")
                                             .content(PushMessageJsonRequestDummy.EMPTY_TITLE_VALUE))
               .andExpect(status().isBadRequest());
    }
}
