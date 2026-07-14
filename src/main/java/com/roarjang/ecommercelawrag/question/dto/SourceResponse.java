package com.roarjang.ecommercelawrag.question.dto;

import com.roarjang.ecommercelawrag.question.model.Source;

public record SourceResponse(
        String documentTitle,
        String article,
        String content
) {

    public static SourceResponse from(Source source) {
        return new SourceResponse(
                source.documentTitle(),
                source.article(),
                source.content()
        );
    }
}
