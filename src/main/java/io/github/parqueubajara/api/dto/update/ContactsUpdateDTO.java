package io.github.parqueubajara.api.dto.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContactsUpdateDTO(
        @Size(max = 100) String name,

        String category,

        String phone,

        @Email(message = "E-mail inválido") String email,

        String description
) {
}
