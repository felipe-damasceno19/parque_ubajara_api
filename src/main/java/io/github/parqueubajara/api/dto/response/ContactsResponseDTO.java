package io.github.parqueubajara.api.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContactsResponseDTO(
        String name,
        String category,
        String phone,
        String email,
        String description
) {
}
