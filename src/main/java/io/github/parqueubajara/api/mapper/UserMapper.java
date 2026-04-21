package io.github.parqueubajara.api.mapper;

import io.github.parqueubajara.api.config.CentralMapperConfig;
import io.github.parqueubajara.api.dto.request.UserRequestDTO;
import io.github.parqueubajara.api.dto.response.UserResponseDTO;
import io.github.parqueubajara.api.dto.update.UserUpdateDTO;
import io.github.parqueubajara.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = CentralMapperConfig .class)
public interface UserMapper {

    @Mapping(source = "role", target = "userRole")
    User toEntity(UserRequestDTO requestDTO);

    @Mapping(source = "userRole", target = "role")
    UserResponseDTO toResponseDTO(User entity);

    void updateEntityFromDto(UserUpdateDTO updateDTO, @MappingTarget User entity);
}
