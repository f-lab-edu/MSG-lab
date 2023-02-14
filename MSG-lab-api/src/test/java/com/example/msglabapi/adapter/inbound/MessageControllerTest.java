package com.example.msglabapi.adapter.inbound;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.msglab.PushMessageJsonRequestDummy;
import com.example.msglabapi.adapter.inbound.exception.ProblemDetail;
import com.example.msglabapi.adapter.inbound.exception.PushMessageApiExceptionAdvice;
import com.example.msglabapi.application.PostMessageService;

class MessageControllerTest {

    private final PostMessageService mockService = mock(PostMessageService.class);
    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(new MessageController(mockService))
            .setControllerAdvice(new PushMessageApiExceptionAdvice())
            .build();

    @Test
    @DisplayName("POST /push-message가 요청되면 MessageController에서 올바른 응답을 내려주는지 테스트")
    void test1() throws Exception {
        mockMvc.perform(
                       post("/push-message")
                               .contentType("application/json")
                               .content(PushMessageJsonRequestDummy.CORRECT_VALUE)
               )
               .andExpect(jsonPath("links").exists())
               .andExpect(status().isOk());
    }

    @Test
    @DisplayName("제목이 비어있는 푸시 메세지가 전송되면 API 스펙을 확인하라는 응답을 내려주는지 테스트")
    void test2() throws Exception {
        final ProblemDetail problem = new ProblemDetail("Fields that should not be blank are blank.",
                                                        "Check out the API specification.");
        mockMvc.perform(
                       post("/push-message")
                               .contentType("application/json")
                               .content(PushMessageJsonRequestDummy.EMPTY_TITLE_VALUE)
               )
               .andExpect(jsonPath("title").exists())
               .andExpect(jsonPath("detail").exists())
               .andExpect(jsonPath("title").value(problem.getTitle()))
               .andExpect(jsonPath("detail").value(problem.getDetail()))
               .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("올바르지 않은 json 형식 데이터로 인해 컨버팅 익셈션이 발생했을때, 문제 상황을 알려주는지 테스트")
    void test3() throws Exception {
        final ProblemDetail problem = new ProblemDetail("A request that cannot be converted.",
                                                        "Make sure it's in the correct JSON format.");

        mockMvc.perform(
                       post("/push-message")
                               .contentType("application/json")
                               .content(PushMessageJsonRequestDummy.MISSING_CURLY_BRACKET)
               )
               .andExpect(jsonPath("title").exists())
               .andExpect(jsonPath("detail").exists())
               .andExpect(jsonPath("title").value(problem.getTitle()))
               .andExpect(jsonPath("detail").value(problem.getDetail()))
               .andExpect(status().isBadRequest());
    }
}
