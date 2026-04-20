package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.config.CentralMapperConfig;
import io.github.parqueubajara.api.dto.request.AirportRequestDTO;
import io.github.parqueubajara.api.dto.response.AirportResponseDTO;
import io.github.parqueubajara.api.model.Airport;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface AirportMapper {

    Airport toEntity(AirportRequestDTO dto);
    AirportResponseDTO toResponseDTO(Airport entity);

}
