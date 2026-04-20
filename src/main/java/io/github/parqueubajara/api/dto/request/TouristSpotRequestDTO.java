package io.github.parqueubajara.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TouristSpotRequestDTO(
        @NotBlank(message = "O nome é obrigatório") @Size(max = 100) String name,

        @NotBlank(message = "A descrição é obrigatória") String description,

        @NotBlank(message = "O endereço é obrigatório") String address,

        String phone,

        @Email(message = "E-mail inválido") String email,

        String webUrl,
        String instagramUrl,

        @NotNull(message = "O status ativo deve ser informado") Boolean active

) {
}
