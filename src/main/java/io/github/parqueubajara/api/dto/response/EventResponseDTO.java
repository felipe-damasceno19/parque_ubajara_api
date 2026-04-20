package io.github.parqueubajara.api.dto.response;

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
