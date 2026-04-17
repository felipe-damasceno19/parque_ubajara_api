package io.github.parqueubajara.api.dto.response;

import io.github.parqueubajara.api.model.enums.AttractionType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record AttractiveResponseDTO(
        UUID id,
        String name,
        String description,
        String address,
        String phone,
        String email,
        String siteUrl,
        String instagramUrl,
        Boolean active,
        String openingHours,
        BigDecimal entryPrice,
        Boolean hasGuide,
        Integer averageVisitDuration,
        AttractionType category
) {
}
