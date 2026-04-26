package io.github.parqueubajara.api.handler;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorResponse(
        LocalDateTime timestamp,
        Integer status,
        String error,
        List<FieldMessage> errors
) {

    public record FieldMessage(String field, String message){}
}
