package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.dto.request.ContactsRequestDTO;
import io.github.parqueubajara.api.dto.response.ContactsResponseDTO;
import io.github.parqueubajara.api.model.Contacts;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactsMapper {

    Contacts toEntity(ContactsRequestDTO requestDTO);
    ContactsResponseDTO toResponseDTO(Contacts entity);
}
