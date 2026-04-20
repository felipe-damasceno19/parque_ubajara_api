package io.github.parqueubajara.api.service;

import io.github.parqueubajara.api.dto.update.HostPointUpdateDTO;
import io.github.parqueubajara.api.mapper.HostPointMapper;
import io.github.parqueubajara.api.model.HostPoint;
import io.github.parqueubajara.api.model.enums.HostType;
import io.github.parqueubajara.api.repository.HostPointRepository;
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
public class HostPointService {

    private final HostPointRepository repository;
    private final HostPointMapper mapper;

    @Transactional(readOnly = true)
    public Optional<HostPoint> findByIdOptional(UUID id){
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public HostPoint findById(UUID id){
        return findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException("Ponto de hospedagem de ID: "+id+" não encontrado!"));
    }

    @Transactional(readOnly = true)
    public Page<HostPoint> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<HostPoint> findByHostType(HostType type, Pageable pageable){
        return repository.findByHostType(type, pageable);
    }

    @Transactional
    public HostPoint save(HostPoint hostPoint){
        return repository.save(hostPoint);
    }

    @Transactional
    public void update(UUID id, HostPointUpdateDTO updateDTO){
        HostPoint hostPoint = findById(id);
        mapper.updateEntityFromDto(updateDTO, hostPoint);
        repository.save(hostPoint);
    }

    @Transactional
    public void delete(UUID id){
        HostPoint hostPoint = findById(id);
        repository.delete(hostPoint);
    }
    
}
