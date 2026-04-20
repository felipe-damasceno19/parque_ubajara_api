package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.config.CentralMapperConfig;
import io.github.parqueubajara.api.dto.request.HostPointRequestDTO;
import io.github.parqueubajara.api.dto.response.HostPointResponseDTO;
import io.github.parqueubajara.api.dto.update.HostPointUpdateDTO;
import io.github.parqueubajara.api.model.HostPoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = CentralMapperConfig.class)
public interface HostPointMapper {

    @Mapping(target = "photos", ignore = true)
    HostPoint toEntity(HostPointRequestDTO requestDTO);
    HostPointResponseDTO toResponseDTO(HostPoint entity);

    void updateEntityFromDto(HostPointUpdateDTO updateDTO, @MappingTarget HostPoint entity);
}
