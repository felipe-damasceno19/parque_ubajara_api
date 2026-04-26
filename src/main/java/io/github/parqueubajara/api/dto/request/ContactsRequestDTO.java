package io.github.parqueubajara.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContactsRequestDTO(
        @NotBlank(message = "Nome obrigatório") String name,

        @Size(max = 50) String category,

        @NotBlank(message = "Telefone obrigatório") String phone,

        @NotBlank(message = "Email obrigatório") @Email String email,

        String description
) {
}
