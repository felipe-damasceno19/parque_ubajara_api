package io.github.parqueubajara.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EventRequestDTO(
        @NotBlank(message = "O nome é obrigatório") String name,

        @NotBlank(message = "A descrição é obrigatória") String description,

        @NotNull
        @FutureOrPresent(message = "O evento não pode começar no passado")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm:ss")
        LocalDateTime startDateTime,

        @NotNull
        @FutureOrPresent(message = "O término não pode ser no passado")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm:ss")
        LocalDateTime endDateTime,

        @NotBlank(message = "O local de realização do evento deve ser informado") String location,

        String registrationUrl,

        @NotNull(message = "O status ativo deve ser informado") Boolean active
) {
}
