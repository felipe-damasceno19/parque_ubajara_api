package io.github.parqueubajara.api.dto.update;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record EventUpdateDTO(
        @Size(max = 100) String name,

        @Size(max = 300) String description,

        @FutureOrPresent(message = "O evento não pode começar no passado")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        @Schema(type = "string", pattern = "dd/MM/yyyy HH:mm:ss", example = "25/04/2026 10:00:00")
        LocalDateTime startDateTime,

        @FutureOrPresent(message = "O término não pode ser no passado")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        @Schema(type = "string", pattern = "dd/MM/yyyy HH:mm:ss", example = "25/04/2026 10:00:00")
        LocalDateTime endDateTime,

        @Size(max = 100) String location,

        String registrationUrl,

        Boolean active
) {
}
