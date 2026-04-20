package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.dto.request.AttractionRequestDTO;
import io.github.parqueubajara.api.dto.response.AttractionResponseDTO;
import io.github.parqueubajara.api.model.Attraction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttractionMapper {

    Attraction toEntity(AttractionRequestDTO requestDTO);
    AttractionResponseDTO toResponseDTO(Attraction entity);
}
