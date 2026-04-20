package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.dto.request.EventRequestDTO;
import io.github.parqueubajara.api.dto.response.EventResponseDTO;
import io.github.parqueubajara.api.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(source = "startDateTime", target = "startDate")
    @Mapping(source = "endDateTime", target = "endDate")
    Event toEntity(EventRequestDTO requestDTO);

    @Mapping(source = "startDate", target = "startDateTime")
    @Mapping(source = "endDate", target = "endDateTime")
    EventResponseDTO toResponseDTO(Event entity);
}
