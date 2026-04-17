package io.github.parqueubajara.api.dto.response;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record RestaurantResponseDTO(
        UUID id,
        String name,
        String description,
        String address,
        String phone,
        String email,
        String siteUrl,
        String instagramUrl,
        Boolean active,
        String cuisineType,
        String openingHours,
        BigDecimal averagePrice,
        Boolean acceptsReservation
) {
}
