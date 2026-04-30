package io.github.parqueubajara.api.service;

import io.github.parqueubajara.api.dto.update.ContactsUpdateDTO;
import io.github.parqueubajara.api.exception.ResourceNotFoundException;
import io.github.parqueubajara.api.mapper.ContactsMapper;
import io.github.parqueubajara.api.model.Contacts;
import io.github.parqueubajara.api.repository.ContactsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

@ExtendWith(MockitoExtension.class)
class ContactsServiceTest {

    @InjectMocks
    private ContactsService service;

    @Mock
    private ContactsRepository repository;

    @Mock
    private ContactsMapper mapper;

    private UUID id;
    private Contacts contacts;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        contacts = new Contacts();
        contacts.setId(id);
        contacts.setEmail("contato@email.com");
    }

    @Test
    void findById_WhenExists_ReturnsContacts() {
        when(repository.findById(id)).thenReturn(Optional.of(contacts));
        Contacts result = service.findById(id);
        assertThat(result).isEqualTo(contacts);
    }

    @Test
    void findById_WhenNotExists_ThrowsResourceNotFoundException() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.findById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void save_WhenEmailNotExists_SavesContacts() {
        when(repository.existsByEmail(contacts.getEmail())).thenReturn(false);
        when(repository.save(contacts)).thenReturn(contacts);
        Contacts result = service.save(contacts);
        assertThat(result).isEqualTo(contacts);
    }

    @Test
    void save_WhenEmailExists_ThrowsRuntimeException() {
        when(repository.existsByEmail(contacts.getEmail())).thenReturn(true);
        assertThatThrownBy(() -> service.save(contacts))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("E-mail já cadastrado");
    }

    @Test
    void update_WhenExists_UpdatesContacts() {
        ContactsUpdateDTO updateDTO = new ContactsUpdateDTO("Policia", "Emergencial", "190"
                , "policia@gmail.com", "Policia de Ubajara");
        when(repository.findById(id)).thenReturn(Optional.of(contacts));
        service.update(id, updateDTO);
        verify(mapper).updateEntityFromDto(updateDTO, contacts);
        verify(repository).save(contacts);
    }

    @Test
    void delete_WhenExists_DeletesContacts() {
        when(repository.findById(id)).thenReturn(Optional.of(contacts));
        service.delete(id);
        verify(repository).delete(contacts);
    }
}