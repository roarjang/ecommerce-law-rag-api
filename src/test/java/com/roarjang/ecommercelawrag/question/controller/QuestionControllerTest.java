package com.roarjang.ecommercelawrag.question.controller;

import com.roarjang.ecommercelawrag.question.model.QuestionAnswer;
import com.roarjang.ecommercelawrag.question.model.Source;
import com.roarjang.ecommercelawrag.question.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 실제 서버를 실행하지 않고 질문 API의 HTTP 요청·응답 형식을 검증하는 MVC 슬라이스 테스트
// Controller가 요청 질문을 서비스에 전달하고, 서비스 결과를 200 OK와 약속된 JSON 구조로 반환하는지 확인

@WebMvcTest(QuestionController.class)
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private QuestionService questionService;

    @Test
    void shouldReturnQuestionAnswerWhenQuestionIsValid() throws Exception {

        // given
        String question = "온라인 구매 상품은 언제까지 청약철회할 수 있나요?";
        UUID questionId =
                UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Instant createdAt = Instant.parse("2026-07-14T08:00:00Z");

        Source source = new Source(
                "전자상거래 등에서의 소비자보호에 관한 법률",
                "제17조",
                "근거 문장"
        );

        QuestionAnswer questionAnswer = new QuestionAnswer(
                questionId,
                question,
                "답변입니다.",
                List.of(source),
                createdAt
        );

        given(questionService.ask(question))
                .willReturn(questionAnswer);

        String requestBody = """
                {
                    "question": "%s"
                }
                """.formatted(question);

        // when & then
        mockMvc.perform(post("/api/v1/questions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                .andExpectAll(
                    status().isOk(),
                    content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                    jsonPath("$.questionId").value(questionId.toString()),
                    jsonPath("$.question").value(question),
                    jsonPath("$.answer").value("답변입니다."),
                    jsonPath("$.sources.length()").value(1),
                    jsonPath("$.sources[0].documentTitle")
                            .value("전자상거래 등에서의 소비자보호에 관한 법률"),
                    jsonPath("$.sources[0].article").value("제17조"),
                    jsonPath("$.sources[0].content").value("근거 문장"),
                    jsonPath("$.createdAt").value(createdAt.toString())
                );

        verify(questionService).ask(question);
    }
}
