package io.github.parqueubajara.api.dto.response;

import java.util.UUID;

public record PhotoResponseDTO(
        UUID id,
        String url,
        String description,
        Integer displayOrder
) {
}
