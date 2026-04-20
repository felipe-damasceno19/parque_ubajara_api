package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.config.CentralMapperConfig;
import io.github.parqueubajara.api.dto.request.AirportRequestDTO;
import io.github.parqueubajara.api.dto.response.AirportResponseDTO;
import io.github.parqueubajara.api.dto.update.AirportUpdateDTO;
import io.github.parqueubajara.api.model.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = CentralMapperConfig.class)
public interface AirportMapper {

    Airport toEntity(AirportRequestDTO dto);
    AirportResponseDTO toResponseDTO(Airport entity);

    void updateEntityFromDto(AirportUpdateDTO updateDTO, @MappingTarget Airport entity);

}
