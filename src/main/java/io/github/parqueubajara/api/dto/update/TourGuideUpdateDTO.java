package io.github.parqueubajara.api.dto.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record TourGuideUpdateDTO(
        @Size(max = 100) String name,

        String phone,

        @Email(message = "E-mail inválido") String email,

        List<String> languages,
        String description,
        Boolean active
) {
}
