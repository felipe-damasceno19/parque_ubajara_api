package io.github.parqueubajara.api.service;

import io.github.parqueubajara.api.exception.ResourceNotFoundException;
import io.github.parqueubajara.api.mapper.UserMapper;
import io.github.parqueubajara.api.model.SystemUser;
import io.github.parqueubajara.api.repository.UserRepository;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @Mock
    private PasswordEncoder encoder;

    private UUID id;
    private SystemUser user;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        user = new SystemUser();
        user.setId(id);
        user.setEmail("felipe@email.com");
        user.setUsername("felipe");
    }

    @Test
    void findById_WhenExists_ReturnsUser() {
        when(repository.findById(id)).thenReturn(Optional.of(user));
        SystemUser result = service.findById(id);
        assertThat(result).isEqualTo(user);
    }

    @Test
    void findById_WhenNotExists_ThrowsResourceNotFoundException() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.findById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void findByEmail_WhenExists_ReturnsUser() {
        when(repository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        SystemUser result = service.findByEmail(user.getEmail());
        assertThat(result).isEqualTo(user);
    }

    @Test
    void findByEmail_WhenNotExists_ThrowsResourceNotFoundException() {
        when(repository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.findByEmail(user.getEmail()))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void findAll_WithoutUsername_ReturnsAllUsers() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<SystemUser> page = new PageImpl<>(List.of(user));
        when(repository.findAll(pageable)).thenReturn(page);
        Page<SystemUser> result = service.findAll(pageable, null);
        assertThat(result).isNotEmpty();
    }

    @Test
    void findAll_WithUsername_ReturnsFilteredUsers() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<SystemUser> page = new PageImpl<>(List.of(user));
        when(repository.findByUsernameContainingIgnoreCase("felipe", pageable))
                .thenReturn(page);
        Page<SystemUser> result = service.findAll(pageable, "felipe");
        assertThat(result).isNotEmpty();
        verify(repository).findByUsernameContainingIgnoreCase("felipe", pageable);
    }

    @Test
    void save_WhenEmailNotExists_SavesUser() {
        when(repository.existsByEmail(user.getEmail())).thenReturn(false);
        when(repository.save(user)).thenReturn(user);
        SystemUser result = service.save(user);
        assertThat(result).isEqualTo(user);
    }

    @Test
    void save_WhenEmailExists_ThrowsRuntimeException() {
        when(repository.existsByEmail(user.getEmail())).thenReturn(true);
        assertThatThrownBy(() -> service.save(user))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("E-mail já cadastrado");
    }

    @Test
    void delete_WhenExists_DeletesUser() {
        when(repository.findById(id)).thenReturn(Optional.of(user));
        service.delete(id);
        verify(repository).delete(user);
    }

    @Test
    void delete_WhenNotExists_ThrowsResourceNotFoundException() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.delete(id))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}