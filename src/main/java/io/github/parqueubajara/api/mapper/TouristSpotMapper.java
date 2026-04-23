package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.config.CentralMapperConfig;
import io.github.parqueubajara.api.dto.request.TouristSpotRequestDTO;
import io.github.parqueubajara.api.dto.response.TouristSpotResponseDTO;
import io.github.parqueubajara.api.dto.update.TouristSpotUpdateDTO;
import io.github.parqueubajara.api.model.TouristSpot;
import org.mapstruct.*;

@Mapper(config = CentralMapperConfig.class, uses = { PhotoMapper.class })
public interface TouristSpotMapper {

    @Mapping(target = "photos", ignore = true)
    TouristSpot toEntity(TouristSpotRequestDTO requestDTO);

    @Mapping(target = "id", source = "id")
    TouristSpotResponseDTO toResponseDTO(TouristSpot entity);

    void updateEntityFromDto(TouristSpotUpdateDTO dto, @MappingTarget TouristSpot entity);
}
