package io.github.parqueubajara.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record AirportRequestDTO(
        @NotBlank(message = "Campo obrigatório!") @Size(min = 3, max = 3) String iataCode,
        @NotBlank(message = "Campo obrigatório!") @Size(max = 50) String city,
        @Positive(message = "A distancia tem que ser um valor positivo") Double distanceKm,
        Integer estimatedTimeMinutes,
        String routeDescription
) {
}
