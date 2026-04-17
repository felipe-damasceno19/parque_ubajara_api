package io.github.parqueubajara.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AirportRequestDTO(
        @NotBlank(message = "Campo obrigatório!")
        String iataCode,
        @NotBlank(message = "Campo obrigatório!")
        String city,
        Double distanceKm,
        Integer estimatedTimeMinutes,
        String routeDescription
) {
}
