package io.github.parqueubajara.api.service;

import io.github.parqueubajara.api.dto.update.AttractionUpdateDTO;
import io.github.parqueubajara.api.exception.ResourceNotFoundException;
import io.github.parqueubajara.api.mapper.AttractionMapper;
import io.github.parqueubajara.api.model.Attraction;
import io.github.parqueubajara.api.model.enums.AttractionType;
import io.github.parqueubajara.api.repository.AttractionRepository;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class AttractionServiceTest {

    @InjectMocks
    private AttractionService service;

    @Mock
    private AttractionRepository repository;

    @Mock
    private AttractionMapper mapper;

    private UUID id;
    private Attraction attraction;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        attraction = new Attraction();
        attraction.setId(id);
        attraction.setEmail("atracao@email.com");
    }

    @Test
    void findById_WhenExists_ReturnsAttraction() {
        when(repository.findById(id)).thenReturn(Optional.of(attraction));
        Attraction result = service.findById(id);
        assertThat(result).isEqualTo(attraction);
    }

    @Test
    void findById_WhenNotExists_ThrowsResourceNotFoundException() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.findById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void findAll_WithoutCategory_ReturnsAllAttractions() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Attraction> page = new PageImpl<>(List.of(attraction));
        when(repository.findAll(pageable)).thenReturn(page);
        Page<Attraction> result = service.findAll(pageable, null);
        assertThat(result).isNotEmpty();
    }

    @Test
    void findAll_WithCategory_ReturnsFilteredAttractions() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Attraction> page = new PageImpl<>(List.of(attraction));
        when(repository.findByCategory(AttractionType.PARK, pageable)).thenReturn(page);
        Page<Attraction> result = service.findAll(pageable, AttractionType.PARK);
        assertThat(result).isNotEmpty();
        verify(repository).findByCategory(AttractionType.PARK, pageable);
    }

    @Test
    void save_WhenEmailNotExists_SavesAttraction() {
        when(repository.existsByEmail(attraction.getEmail())).thenReturn(false);
        when(repository.save(attraction)).thenReturn(attraction);
        Attraction result = service.save(attraction);
        assertThat(result).isEqualTo(attraction);
    }

    @Test
    void save_WhenEmailExists_ThrowsRuntimeException() {
        when(repository.existsByEmail(attraction.getEmail())).thenReturn(true);
        assertThatThrownBy(() -> service.save(attraction))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("E-mail já cadastrado");
    }

    @Test
    void update_WhenExists_UpdatesAttraction() {
        AttractionUpdateDTO updateDTO = new AttractionUpdateDTO("Parque", "parque ecologico", "Centro", "88995529874",
                "parque@gmail.com", "parque.com.br", "@parqueubajar", true, "12:00", BigDecimal.valueOf(90)
                , true, 10, AttractionType.PARK);
        when(repository.findById(id)).thenReturn(Optional.of(attraction));
        service.update(id, updateDTO);
        verify(mapper).updateEntityFromDto(updateDTO, attraction);
        verify(repository).save(attraction);
    }

    @Test
    void delete_WhenExists_DeletesAttraction() {
        when(repository.findById(id)).thenReturn(Optional.of(attraction));
        service.delete(id);
        verify(repository).delete(attraction);
    }

}
