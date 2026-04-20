package io.github.parqueubajara.api.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record TourGuideResponseDTO(
        UUID id,
        String name,
        String phone,
        String email,
        List<String> languages,
        String description,
        Boolean active,
        List<PhotoResponseDTO> photos
) {
}
