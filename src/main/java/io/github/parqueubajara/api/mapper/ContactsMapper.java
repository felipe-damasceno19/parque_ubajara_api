package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.config.CentralMapperConfig;
import io.github.parqueubajara.api.dto.request.ContactsRequestDTO;
import io.github.parqueubajara.api.dto.response.ContactsResponseDTO;
import io.github.parqueubajara.api.model.Contacts;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface ContactsMapper {

    Contacts toEntity(ContactsRequestDTO requestDTO);
    ContactsResponseDTO toResponseDTO(Contacts entity);
}
