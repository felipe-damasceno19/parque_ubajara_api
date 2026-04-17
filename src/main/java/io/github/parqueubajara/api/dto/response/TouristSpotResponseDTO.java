package io.github.parqueubajara.api.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record TouristSpotResponseDTO(
        UUID id,
        String name,
        String description,
        String address,
        String phone,
        String email,
        String siteUrl,
        String instagramUrl,
        Boolean active
) {
}
