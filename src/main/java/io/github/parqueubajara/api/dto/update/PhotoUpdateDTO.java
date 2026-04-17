package io.github.parqueubajara.api.dto.update;

import jakarta.validation.constraints.NotBlank;

public record PhotoUpdateDTO(
        @NotBlank String description,
        Integer displayOrder
) {
}
