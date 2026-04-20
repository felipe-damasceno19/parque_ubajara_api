package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.dto.request.AttractiveRequestDTO;
import io.github.parqueubajara.api.dto.response.AttractiveResponseDTO;
import io.github.parqueubajara.api.model.Attractive;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttractiveMapper {

    Attractive toEntity(AttractiveRequestDTO requestDTO);
    AttractiveResponseDTO toResponseDTO(Attractive entity);
}
