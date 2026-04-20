package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.dto.request.TouristSpotRequestDTO;
import io.github.parqueubajara.api.dto.response.TouristSpotResponseDTO;
import io.github.parqueubajara.api.dto.update.TouristSpotUpdateDTO;
import io.github.parqueubajara.api.model.TouristSpot;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = { PhotoMapper.class })
public interface TouristSpotMapper {

    @Mapping(target = "photos", ignore = true)
    TouristSpot toEntity(TouristSpotRequestDTO requestDTO);

    TouristSpotResponseDTO toResponseDTO(TouristSpot entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photos", ignore = true)
    void updateEntityFromDto(TouristSpotUpdateDTO dto, @MappingTarget TouristSpot entity);
}
