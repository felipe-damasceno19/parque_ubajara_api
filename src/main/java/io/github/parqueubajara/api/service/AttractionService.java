package io.github.parqueubajara.api.service;

import io.github.parqueubajara.api.dto.update.AttractionUpdateDTO;
import io.github.parqueubajara.api.mapper.AttractionMapper;
import io.github.parqueubajara.api.model.Attraction;
import io.github.parqueubajara.api.model.enums.AttractionType;
import io.github.parqueubajara.api.repository.AttractionRepository;
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
public class AttractionService {

    private final AttractionRepository repository;
    private final AttractionMapper mapper;

    @Transactional(readOnly = true)
    public Optional<Attraction> findByIdOptional(UUID id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Attraction findById(UUID id) {
        return findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException("Atração com ID: " + id + " não encontrada!"));
    }

    @Transactional(readOnly = true)
    public Page<Attraction> findAll(Pageable pageable, AttractionType category){
        if(category != null){
            return repository.findByCategory(category, pageable);
        }
        return repository.findAll(pageable);
    }

    @Transactional
    public Attraction save(Attraction attraction) {
        if(repository.existsByEmail(attraction.getEmail())){
            throw new RuntimeException("E-mail já cadastrado");
        }
        return repository.save(attraction);
    }

    @Transactional
    public void update(UUID id, AttractionUpdateDTO updateDTO){
        Attraction attraction = findById(id);
        mapper.updateEntityFromDto(updateDTO, attraction);
        repository.save(attraction);
    }

    @Transactional
    public void delete(UUID id){
        Attraction attraction = findById(id);
        repository.delete(attraction);
    }
}