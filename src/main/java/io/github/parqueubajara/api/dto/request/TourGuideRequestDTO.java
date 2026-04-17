package io.github.parqueubajara.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TourGuideRequestDTO(
        @NotBlank(message = "O nome do guia deve ser informado") String tourGuide,

        @NotBlank(message = "O telefone deve ser informado") String phone,

        @NotBlank(message = "Email obrigatório!") String email,

        List<String> languages,
        String description,

        @NotNull(message = "O status ativo deve ser informado") Boolean active
) {
}
