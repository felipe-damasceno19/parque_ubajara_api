package io.github.parqueubajara.api.dto.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record TouristSpotUpdateDTO(
        @Size(max = 100) String name,

        @Size(max = 300) String description,

        @Size(max = 100) String address,

        String phone,

        @Email(message = "E-mail inválido") String email,

        String webUrl,
        String instagramUrl,

        Boolean active
) {
}
