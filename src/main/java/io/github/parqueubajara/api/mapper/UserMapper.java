package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.dto.request.UserRequestDTO;
import io.github.parqueubajara.api.dto.response.UserResponseDTO;
import io.github.parqueubajara.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role", target = "userRole")
    User toEntity(UserRequestDTO requestDTO);

    @Mapping(source = "userRole", target = "role")
    UserResponseDTO toResponseDTO(User entity);
}
