package io.github.parqueubajara.api.service;

import io.github.parqueubajara.api.dto.update.TouristSpotUpdateDTO;
import io.github.parqueubajara.api.exception.ResourceNotFoundException;
import io.github.parqueubajara.api.mapper.TouristSpotMapper;
import io.github.parqueubajara.api.model.TouristSpot;
import io.github.parqueubajara.api.repository.TouristSpotRepository;
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
class TouristSpotServiceTest {

    @InjectMocks
    private TouristSpotService service;

    @Mock
    private TouristSpotRepository repository;

    @Mock
    private TouristSpotMapper mapper;

    private UUID id;
    private TouristSpot spot;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        spot = new TouristSpot();
        spot.setId(id);
        spot.setEmail("parque@email.com");
    }

    @Test
    void findById_WhenExists_ReturnsTouristSpot() {
        when(repository.findById(id)).thenReturn(Optional.of(spot));
        TouristSpot result = service.findById(id);
        assertThat(result).isEqualTo(spot);
    }

    @Test
    void findById_WhenNotExists_ThrowsResourceNotFoundException() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.findById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void save_WhenEmailNotExists_SavesSpot() {
        when(repository.existsByEmail(spot.getEmail())).thenReturn(false);
        when(repository.save(spot)).thenReturn(spot);
        TouristSpot result = service.save(spot);
        assertThat(result).isEqualTo(spot);
    }

    @Test
    void save_WhenEmailExists_ThrowsRuntimeException() {
        when(repository.existsByEmail(spot.getEmail())).thenReturn(true);
        assertThatThrownBy(() -> service.save(spot))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("E-mail já cadastrado");
    }

    @Test
    void findAll_ReturnsPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<TouristSpot> page = new PageImpl<>(List.of(spot));
        when(repository.findAll(pageable)).thenReturn(page);
        Page<TouristSpot> result = service.findAll(pageable);
        assertThat(result).isNotEmpty();
    }

    @Test
    void update_WhenExists_UpdatesSpot() {
        TouristSpotUpdateDTO updateDTO = new TouristSpotUpdateDTO("Trilha da Serra", "trilha de 20km", "Parque de ubajara"
                , "88992428764", "trilha@gmail.com", "trilha.com.br", "@trilhaSerra", true);
        when(repository.findById(id)).thenReturn(Optional.of(spot));
        service.update(id, updateDTO);
        verify(mapper).updateEntityFromDto(updateDTO, spot);
        verify(repository).save(spot);
    }

    @Test
    void delete_WhenExists_DeletesSpot() {
        when(repository.findById(id)).thenReturn(Optional.of(spot));
        service.delete(id);
        verify(repository).delete(spot);
    }
}