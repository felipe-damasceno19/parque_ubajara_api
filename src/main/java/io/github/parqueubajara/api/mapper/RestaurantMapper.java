package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.dto.request.RestaurantRequestDTO;
import io.github.parqueubajara.api.dto.response.RestaurantResponseDTO;
import io.github.parqueubajara.api.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    Restaurant toEntity(RestaurantRequestDTO requestDTO);
    RestaurantResponseDTO toResponseDTO(Restaurant entity);
}
