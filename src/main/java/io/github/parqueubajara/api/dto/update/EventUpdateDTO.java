package io.github.parqueubajara.api.dto.update;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record EventUpdateDTO(
        @Size(max = 100) String name,

        @Size(max = 300) String description,

        @FutureOrPresent(message = "O evento não pode começar no passado")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm:ss")
        LocalDateTime startDateTime,

        @FutureOrPresent(message = "O término não pode ser no passado")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm:ss")
        LocalDateTime endDateTime,

        @Size(max = 100) String location,

        String registrationUrl,

        Boolean active
) {
}
