package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.config.CentralMapperConfig;
import io.github.parqueubajara.api.dto.request.TourGuideRequestDTO;
import io.github.parqueubajara.api.dto.response.TourGuideResponseDTO;
import io.github.parqueubajara.api.model.TourGuide;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralMapperConfig.class, uses = {PhotoMapper.class})
public interface TourGuideMapper {

    @Mapping(target = "photos", ignore = true)
    TourGuide toEntity(TourGuideRequestDTO requestDTO);

    TourGuideResponseDTO toResponseDTO(TourGuide entity);
}
