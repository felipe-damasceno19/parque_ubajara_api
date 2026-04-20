package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.config.CentralMapperConfig;
import io.github.parqueubajara.api.dto.request.HostPointRequestDTO;
import io.github.parqueubajara.api.dto.response.HostPointResponseDTO;
import io.github.parqueubajara.api.model.HostPoint;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface HostPointMapper {

    HostPoint toEntity(HostPointRequestDTO requestDTO);
    HostPointResponseDTO toResponseDTO(HostPoint entity);
}
