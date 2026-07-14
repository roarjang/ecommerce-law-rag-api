package com.roarjang.ecommercelawrag.question.dto;

import com.roarjang.ecommercelawrag.question.model.QuestionAnswer;
import com.roarjang.ecommercelawrag.question.model.Source;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record QuestionResponse(
        UUID questionId,
        String question,
        String answer,
        List<SourceResponse> sources,
        Instant createdAt
) {
    public static QuestionResponse from(QuestionAnswer answer) {

        List<SourceResponse> sources = answer.sources().stream()
                .map(SourceResponse::from)
                .toList();

        return new QuestionResponse(
            answer.questionId(),
            answer.question(),
            answer.answer(),
            sources,
            answer.createdAt()
        );
    }
}
