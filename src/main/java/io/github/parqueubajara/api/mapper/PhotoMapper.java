package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.config.CentralMapperConfig;
import io.github.parqueubajara.api.dto.response.PhotoResponseDTO;
import io.github.parqueubajara.api.model.Photo;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface PhotoMapper {

    PhotoResponseDTO toResponseDTO(Photo photo);
}