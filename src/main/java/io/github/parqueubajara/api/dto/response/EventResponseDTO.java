package io.github.parqueubajara.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record EventResponseDTO(
        UUID id,
        String name,
        String description,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        String location,
        String registrationUrl,
        Boolean active,
        List<PhotoResponseDTO> photos
) {
}
