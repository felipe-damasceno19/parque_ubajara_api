package io.github.parqueubajara.api.service;

import io.github.parqueubajara.api.dto.update.UserUpdateDTO;
import io.github.parqueubajara.api.mapper.UserMapper;
import io.github.parqueubajara.api.model.User;
import io.github.parqueubajara.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Transactional(readOnly = true)
    public Optional<User> findByIdOptional(UUID id){
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public User findById(UUID id){
        return findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário de ID: "+ id +" não encontrado"));
    }

    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username){
        return repository.findByUsername(username);
    }

    @Transactional
    public User save(User user){
        return repository.save(user);
    }

    @Transactional
    public void update(UUID id, UserUpdateDTO updateDTO){
        User user = findById(id);
        mapper.updateEntityFromDto(updateDTO, user);
        repository.save(user);
    }

    @Transactional
    public void delete(UUID id){
        User user = findById(id);
        repository.delete(user);
    }


}
