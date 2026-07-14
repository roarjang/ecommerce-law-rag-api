package com.roarjang.ecommercelawrag.question.service;

import com.roarjang.ecommercelawrag.question.model.QuestionAnswer;
import com.roarjang.ecommercelawrag.question.model.Source;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class MockQuestionServiceTest {

    private final QuestionService questionService =
            new MockQuestionService();

    @Test
    void shouldReturnMockAnswerAndSourcesForQuestion() {
        // given
        String question = "온라인 구매 상품은 언제까지 청약철회할 수 있나요?";
        Instant before = Instant.now();

        // when
        QuestionAnswer result = questionService.ask(question);
        Instant after = Instant.now();

        // then
        Source source = result.sources().get(0);

        assertThat(result).isNotNull();
        assertThat(result.questionId()).isNotNull();
        assertThat(result.question()).isEqualTo(question);
        assertThat(result.answer()).isNotBlank();
        assertThat(result.sources()).isNotEmpty();
        assertThat(source.documentTitle()).isNotBlank();
        assertThat(source.article()).isNotBlank();
        assertThat(source.content()).isNotBlank();
        assertThat(result.createdAt()).isAfterOrEqualTo(before);
        assertThat(result.createdAt()).isBeforeOrEqualTo(after);
    }
}
