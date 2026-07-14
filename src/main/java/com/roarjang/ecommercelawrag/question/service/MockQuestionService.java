package com.roarjang.ecommercelawrag.question.service;

import com.roarjang.ecommercelawrag.question.model.QuestionAnswer;
import com.roarjang.ecommercelawrag.question.model.Source;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class MockQuestionService implements QuestionService {

    @Override
    public QuestionAnswer ask(String question) {
        Source source = new Source(
                "전자상거래 등에서의 소비자보호에 관한 법률",
                "제17조",
                "근거 문장"
        );

        return new QuestionAnswer(
                UUID.randomUUID(),
                question,
                "답변입니다.",
                List.of(source),
                Instant.now()
        );
    }
}
