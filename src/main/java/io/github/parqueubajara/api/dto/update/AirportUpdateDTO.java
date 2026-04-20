package io.github.parqueubajara.api.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AirportUpdateDTO(
        String iataCode,
        @Size(max = 100) String city,
        Double distanceKm,
        Integer estimatedTimeMinutes,
        String routeDescription
) {
}
