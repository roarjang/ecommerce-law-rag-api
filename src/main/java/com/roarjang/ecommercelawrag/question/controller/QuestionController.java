package com.roarjang.ecommercelawrag.question.controller;

import com.roarjang.ecommercelawrag.question.dto.QuestionRequest;
import com.roarjang.ecommercelawrag.question.dto.QuestionResponse;
import com.roarjang.ecommercelawrag.question.model.QuestionAnswer;
import com.roarjang.ecommercelawrag.question.service.QuestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public QuestionResponse ask(@RequestBody QuestionRequest request) {

        String question = request.question();

        QuestionAnswer questionAnswer = questionService.ask(question);

        return QuestionResponse.from(questionAnswer);
    }
}
