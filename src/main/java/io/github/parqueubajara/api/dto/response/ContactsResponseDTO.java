package io.github.parqueubajara.api.dto.response;

import java.util.UUID;

public record ContactsResponseDTO(
        UUID id,
        String name,
        String category,
        String phone,
        String email,
        String description
) {
}
