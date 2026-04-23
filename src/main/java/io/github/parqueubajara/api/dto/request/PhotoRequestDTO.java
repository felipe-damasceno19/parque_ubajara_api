package io.github.parqueubajara.api.dto.request;

import jakarta.validation.constraints.Size;

public record PhotoRequestDTO(
        @Size(max = 100) String description,
        String displayOrder
) {
}
