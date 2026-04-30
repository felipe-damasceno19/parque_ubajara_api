package io.github.parqueubajara.api.service;

import io.github.parqueubajara.api.dto.update.RestaurantUpdateDTO;
import io.github.parqueubajara.api.exception.ResourceNotFoundException;
import io.github.parqueubajara.api.mapper.RestaurantMapper;
import io.github.parqueubajara.api.model.Restaurant;
import io.github.parqueubajara.api.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService service;

    @Mock
    private RestaurantRepository repository;

    @Mock
    private RestaurantMapper mapper;

    private UUID id;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        restaurant = new Restaurant();
        restaurant.setId(id);
        restaurant.setEmail("restaurante@email.com");
    }

    @Test
    void findById_WhenExists_ReturnsRestaurant() {
        when(repository.findById(id)).thenReturn(Optional.of(restaurant));
        Restaurant result = service.findById(id);
        assertThat(result).isEqualTo(restaurant);
    }

    @Test
    void findById_WhenNotExists_ThrowsResourceNotFoundException() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.findById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void save_WhenEmailNotExists_SavesRestaurant() {
        when(repository.existsByEmail(restaurant.getEmail())).thenReturn(false);
        when(repository.save(restaurant)).thenReturn(restaurant);
        Restaurant result = service.save(restaurant);
        assertThat(result).isEqualTo(restaurant);
    }

    @Test
    void save_WhenEmailExists_ThrowsRuntimeException() {
        when(repository.existsByEmail(restaurant.getEmail())).thenReturn(true);
        assertThatThrownBy(() -> service.save(restaurant))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("E-mail já cadastrado");
    }

    @Test
    void update_WhenExists_UpdatesRestaurant() {
        RestaurantUpdateDTO updateDTO = new RestaurantUpdateDTO("Pizzaria", "pizzaria tradicional", "Centro", "88995529874",
                "pizzaria@gmail.com", "pizza.com.br", "@pizzaria", true, "Italiana/Pizzaria"
                , "10:00", BigDecimal.valueOf(70.0), true);
        when(repository.findById(id)).thenReturn(Optional.of(restaurant));
        service.update(id, updateDTO);
        verify(mapper).updateEntityFromDto(updateDTO, restaurant);
    }

    @Test
    void delete_WhenExists_DeletesRestaurant() {
        when(repository.findById(id)).thenReturn(Optional.of(restaurant));
        service.delete(id);
        verify(repository).delete(restaurant);
    }
}