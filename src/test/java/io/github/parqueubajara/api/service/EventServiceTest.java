package io.github.parqueubajara.api.service;

import io.github.parqueubajara.api.exception.ResourceNotFoundException;
import io.github.parqueubajara.api.mapper.EventMapper;
import io.github.parqueubajara.api.model.Event;
import io.github.parqueubajara.api.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @InjectMocks
    private EventService service;

    @Mock
    private EventRepository repository;

    @Mock
    private EventMapper mapper;

    private UUID id;
    private Event event;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        event = new Event();
        event.setId(id);
    }

    @Test
    void findById_WhenExists_ReturnsEvent() {
        when(repository.findById(id)).thenReturn(Optional.of(event));
        Event result = service.findById(id);
        assertThat(result).isEqualTo(event);
    }

    @Test
    void findById_WhenNotExists_ThrowsResourceNotFoundException() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.findById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void findAll_WithoutDates_ReturnsAllEvents() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Event> page = new PageImpl<>(List.of(event));
        when(repository.findAll(pageable)).thenReturn(page);
        Page<Event> result = service.findAll(pageable, null, null);
        assertThat(result).isNotEmpty();
    }

    @Test
    void findAll_WithDates_ReturnsFilteredEvents() {
        Pageable pageable = PageRequest.of(0, 10);
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(7);
        Page<Event> page = new PageImpl<>(List.of(event));
        when(repository.findByStartDateBetween(start, end, pageable)).thenReturn(page);
        Page<Event> result = service.findAll(pageable, start, end);
        assertThat(result).isNotEmpty();
        verify(repository).findByStartDateBetween(start, end, pageable);
    }

    @Test
    void save_ReturnsEvent() {
        when(repository.save(event)).thenReturn(event);
        Event result = service.save(event);
        assertThat(result).isEqualTo(event);
    }

    @Test
    void delete_WhenExists_DeletesEvent() {
        when(repository.findById(id)).thenReturn(Optional.of(event));
        service.delete(id);
        verify(repository).delete(event);
    }
}