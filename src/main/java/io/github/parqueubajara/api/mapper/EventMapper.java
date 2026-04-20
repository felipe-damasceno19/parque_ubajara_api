package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.dto.request.EventRequestDTO;
import io.github.parqueubajara.api.dto.response.EventResponseDTO;
import io.github.parqueubajara.api.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

// Adicionamos o "uses" para ele enxergar como converter as fotos
@Mapper(componentModel = "spring", uses = { PhotoMapper.class })
public interface EventMapper {

    @Mapping(source = "startDateTime", target = "startDate")
    @Mapping(source = "endDateTime", target = "endDate")
    @Mapping(target = "photos", ignore = true)
    Event toEntity(EventRequestDTO requestDTO);

    @Mapping(source = "startDate", target = "startDateTime")
    @Mapping(source = "endDate", target = "endDateTime")
    EventResponseDTO toResponseDTO(Event entity);
}