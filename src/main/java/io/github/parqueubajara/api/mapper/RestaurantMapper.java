package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.config.CentralMapperConfig;
import io.github.parqueubajara.api.dto.request.RestaurantRequestDTO;
import io.github.parqueubajara.api.dto.response.RestaurantResponseDTO;
import io.github.parqueubajara.api.dto.update.RestaurantUpdateDTO;
import io.github.parqueubajara.api.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = CentralMapperConfig.class)
public interface RestaurantMapper {

    Restaurant toEntity(RestaurantRequestDTO requestDTO);
    RestaurantResponseDTO toResponseDTO(Restaurant entity);

    void updateEntityFromDto(RestaurantUpdateDTO updateDTO, @MappingTarget Restaurant entity);
}
