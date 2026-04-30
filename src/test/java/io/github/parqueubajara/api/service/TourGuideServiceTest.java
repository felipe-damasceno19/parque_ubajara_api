package io.github.parqueubajara.api.service;

import io.github.parqueubajara.api.dto.update.TourGuideUpdateDTO;
import io.github.parqueubajara.api.exception.ResourceNotFoundException;
import io.github.parqueubajara.api.mapper.TourGuideMapper;
import io.github.parqueubajara.api.model.TourGuide;
import io.github.parqueubajara.api.repository.TourGuideRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class TourGuideServiceTest {

    @InjectMocks
    private TourGuideService service;

    @Mock
    private TourGuideRepository repository;

    @Mock
    private TourGuideMapper mapper;

    private UUID id;
    private TourGuide guide;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        guide = new TourGuide();
        guide.setId(id);
        guide.setEmail("guia@email.com");
    }

    @Test
    void findById_WhenExists_ReturnsTourGuide() {
        when(repository.findById(id)).thenReturn(Optional.of(guide));
        TourGuide result = service.findById(id);
        assertThat(result).isEqualTo(guide);
    }

    @Test
    void findById_WhenNotExists_ThrowsResourceNotFoundException() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.findById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void save_WhenEmailNotExists_SavesGuide() {
        when(repository.existsByEmail(guide.getEmail())).thenReturn(false);
        when(repository.save(guide)).thenReturn(guide);
        TourGuide result = service.save(guide);
        assertThat(result).isEqualTo(guide);
    }

    @Test
    void save_WhenEmailExists_ThrowsRuntimeException() {
        when(repository.existsByEmail(guide.getEmail())).thenReturn(true);
        assertThatThrownBy(() -> service.save(guide))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("E-mail já cadastrado");
    }

    @Test
    void update_WhenExists_UpdatesGuide() {
        TourGuideUpdateDTO updateDTO = new TourGuideUpdateDTO("Jorge", "88996628748", "jorge@gmail.com"
                , List.of("Portugues", "ingles"), "Simpático", true);
        when(repository.findById(id)).thenReturn(Optional.of(guide));
        service.update(id, updateDTO);
        verify(mapper).updateEntityFromDto(updateDTO, guide);
        verify(repository).save(guide);
    }

    @Test
    void delete_WhenExists_DeletesGuide() {
        when(repository.findById(id)).thenReturn(Optional.of(guide));
        service.delete(id);
        verify(repository).delete(guide);
    }
}
