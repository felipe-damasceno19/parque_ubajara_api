package io.github.parqueubajara.api.service;

import io.github.parqueubajara.api.exception.ResourceNotFoundException;
import io.github.parqueubajara.api.mapper.HostPointMapper;
import io.github.parqueubajara.api.model.HostPoint;
import io.github.parqueubajara.api.model.enums.HostType;
import io.github.parqueubajara.api.repository.HostPointRepository;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class HostPointServiceTest {

    @InjectMocks
    private HostPointService service;

    @Mock
    private HostPointRepository repository;

    @Mock
    private HostPointMapper mapper;

    private UUID id;
    private HostPoint hostPoint;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        hostPoint = new HostPoint();
        hostPoint.setId(id);
        hostPoint.setEmail("hospedagem@email.com");
    }

    @Test
    void findById_WhenExists_ReturnsHostPoint() {
        when(repository.findById(id)).thenReturn(Optional.of(hostPoint));
        HostPoint result = service.findById(id);
        assertThat(result).isEqualTo(hostPoint);
    }

    @Test
    void findById_WhenNotExists_ThrowsResourceNotFoundException() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.findById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void findAll_WithoutType_ReturnsAllHostPoints() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<HostPoint> page = new PageImpl<>(List.of(hostPoint));
        when(repository.findAll(pageable)).thenReturn(page);
        Page<HostPoint> result = service.findAll(pageable, null);
        assertThat(result).isNotEmpty();
    }

    @Test
    void findAll_WithType_ReturnsFilteredHostPoints() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<HostPoint> page = new PageImpl<>(List.of(hostPoint));
        when(repository.findByHostType(HostType.HOTEL, pageable)).thenReturn(page);
        Page<HostPoint> result = service.findAll(pageable, HostType.HOTEL);
        assertThat(result).isNotEmpty();
        verify(repository).findByHostType(HostType.HOTEL, pageable);
    }

    @Test
    void save_WhenEmailNotExists_SavesHostPoint() {
        when(repository.existsByEmail(hostPoint.getEmail())).thenReturn(false);
        when(repository.save(hostPoint)).thenReturn(hostPoint);
        HostPoint result = service.save(hostPoint);
        assertThat(result).isEqualTo(hostPoint);
    }

    @Test
    void save_WhenEmailExists_ThrowsRuntimeException() {
        when(repository.existsByEmail(hostPoint.getEmail())).thenReturn(true);
        assertThatThrownBy(() -> service.save(hostPoint))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("E-mail já cadastrado");
    }

    @Test
    void delete_WhenExists_DeletesHostPoint() {
        when(repository.findById(id)).thenReturn(Optional.of(hostPoint));
        service.delete(id);
        verify(repository).delete(hostPoint);
    }
}