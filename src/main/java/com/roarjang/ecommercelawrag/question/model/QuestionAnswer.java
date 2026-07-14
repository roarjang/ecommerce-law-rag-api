package com.roarjang.ecommercelawrag.question.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record QuestionAnswer(
        UUID questionId,
        String question,
        String answer,
        List<Source> sources,
        Instant createdAt
) {
}
