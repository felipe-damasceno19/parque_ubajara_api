package io.github.parqueubajara.api.service;

import io.github.parqueubajara.api.dto.update.AirportUpdateDTO;
import io.github.parqueubajara.api.exception.ResourceNotFoundException;
import io.github.parqueubajara.api.mapper.AirportMapper;
import io.github.parqueubajara.api.model.Airport;
import io.github.parqueubajara.api.repository.AirportRepository;
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
class AirportServiceTest {

    @InjectMocks
    private AirportService service;

    @Mock
    private AirportRepository repository;

    @Mock
    private AirportMapper mapper;

    private UUID id;
    private Airport airport;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        airport = new Airport();
        airport.setId(id);
    }

    @Test
    void update_WhenExists_UpdatesAirport() {
        // Ajuste: Instancie o record com os valores necessários (Ex: nome, cidade, etc)
        AirportUpdateDTO updateDTO = new AirportUpdateDTO("JJD", "São Benedito", 100.0, 60,"segura");

        when(repository.findById(id)).thenReturn(Optional.of(airport));

        service.update(id, updateDTO);

        verify(mapper).updateEntityFromDto(updateDTO, airport);
        verify(repository).save(airport);
    }

    @Test
    void update_WhenNotExists_ThrowsResourceNotFoundException() {
        // Ajuste: Instancie o record aqui também
        AirportUpdateDTO updateDTO = new AirportUpdateDTO("JJD", "São Benedito", 100.0, 60,"segura");

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.update(id, updateDTO))
                .isInstanceOf(ResourceNotFoundException.class);
    }
    @Test
    void findAll_ReturnsPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Airport> page = new PageImpl<>(List.of(airport));
        when(repository.findAll(pageable)).thenReturn(page);
        Page<Airport> result = service.findAll(pageable);
        assertThat(result).isNotEmpty();
    }

    @Test
    void save_ReturnsAirport() {
        when(repository.save(airport)).thenReturn(airport);
        Airport result = service.save(airport);
        assertThat(result).isEqualTo(airport);
        verify(repository).save(airport);
    }

    @Test
    void delete_WhenExists_DeletesAirport() {
        when(repository.findById(id)).thenReturn(Optional.of(airport));
        service.delete(id);
        verify(repository).delete(airport);
    }

    @Test
    void delete_WhenNotExists_ThrowsResourceNotFoundException() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.delete(id))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}