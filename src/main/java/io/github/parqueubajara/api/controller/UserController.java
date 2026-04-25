package io.github.parqueubajara.api.controller;

import io.github.parqueubajara.api.dto.request.UserRequestDTO;
import io.github.parqueubajara.api.dto.response.UserResponseDTO;
import io.github.parqueubajara.api.dto.update.UserUpdateDTO;
import io.github.parqueubajara.api.mapper.UserMapper;
import io.github.parqueubajara.api.model.User;
import io.github.parqueubajara.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements GenericController{

    private final UserService service;
    private final UserMapper mapper;


    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> findAll(@PageableDefault(size = 10)Pageable pageable,
                                                         @RequestParam(required = false) String username){
        Page<User> pageEntity = service.findAll(pageable, username);
        return ResponseEntity.ok(pageEntity.map(mapper::toResponseDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable UUID id){
        User user = service.findById(id);
        return ResponseEntity.ok(mapper.toResponseDTO(user));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@Valid @RequestBody UserRequestDTO requestDTO){
        User user = mapper.toEntity(requestDTO);
        service.save(user);
        URI location = generateHeaderLocation(user.getId());

        return ResponseEntity.created(location).body(mapper.toResponseDTO(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody @Valid UserUpdateDTO updateDTO){
        service.update(id, updateDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
