package io.github.parqueubajara.api.service;

import io.github.parqueubajara.api.dto.update.EventUpdateDTO;
import io.github.parqueubajara.api.mapper.EventMapper;
import io.github.parqueubajara.api.model.Event;
import io.github.parqueubajara.api.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;
    private final EventMapper mapper;

    @Transactional(readOnly = true)
    public Optional<Event> findByIdOptional(UUID id){
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Event findById(UUID id){
        return findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento de ID: "+ id +" não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Event> findByCreateDateBetween(LocalDateTime start, LocalDateTime end){
        return repository.findByCreatedDateBetween(start, end);
    }

    @Transactional
    public Event save(Event event){
        return repository.save(event);
    }

    @Transactional
    public void update(UUID id, EventUpdateDTO updateDTO){
        Event event = findById(id);
        mapper.updateEntityFromDto(updateDTO, event);
        repository.save(event);
    }

    @Transactional
    public void delete(UUID id){
        Event event = findById(id);
        repository.delete(event);
    }
}
