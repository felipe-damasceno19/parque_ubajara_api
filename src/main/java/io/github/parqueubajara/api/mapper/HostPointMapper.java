package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.dto.request.HostPointRequestDTO;
import io.github.parqueubajara.api.dto.response.HostPointResponseDTO;
import io.github.parqueubajara.api.model.HostPoint;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HostPointMapper {

    HostPoint toEntity(HostPointRequestDTO requestDTO);
    HostPointResponseDTO toResponseDTO(HostPoint entity);
}
