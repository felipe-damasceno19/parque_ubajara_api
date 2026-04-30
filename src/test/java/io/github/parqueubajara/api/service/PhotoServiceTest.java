package io.github.parqueubajara.api.service;

import io.github.parqueubajara.api.dto.response.PhotoResponseDTO;
import io.github.parqueubajara.api.exception.ResourceNotFoundException;
import io.github.parqueubajara.api.mapper.PhotoMapper;
import io.github.parqueubajara.api.model.Photo;
import io.github.parqueubajara.api.repository.PhotoRepository;
import io.github.parqueubajara.api.service.infra.FileValidationService;
import io.github.parqueubajara.api.service.infra.S3StorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)

@ExtendWith(MockitoExtension.class)
class PhotoServiceTest {

    @InjectMocks
    private PhotoService service;

    @Mock
    private PhotoRepository repository;

    @Mock
    private S3StorageService storageService;

    @Mock
    private PhotoMapper mapper;

    @Mock
    private FileValidationService validationService;

    @Mock
    private MultipartFile file;

    private UUID id;
    private Photo photo;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        photo = new Photo();
        photo.setId(id);
        photo.setUrl("https://bucket.s3.sa-east-1.amazonaws.com/uuid-foto.jpg");
        photo.setStorageKey("uuid-foto.jpg");
    }

    @Test
    void findById_WhenExists_ReturnsPhoto() {
        when(repository.findById(id)).thenReturn(Optional.of(photo));
        Photo result = service.findById(id);
        assertThat(result).isEqualTo(photo);
    }

    @Test
    void findById_WhenNotExists_ThrowsResourceNotFoundException() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.findById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(id.toString());
    }

    @Test
    void upload_WhenValidFile_SavesPhotoAndReturnsResponse() throws IOException {
        String url = "https://bucket.s3.sa-east-1.amazonaws.com/uuid-foto.jpg";
        PhotoResponseDTO responseDTO = new PhotoResponseDTO(UUID.randomUUID(), url,"foto teste", 1);

        when(storageService.upload(file)).thenReturn(url);
        when(repository.save(any(Photo.class))).thenReturn(photo);
        when(mapper.toResponseDTO(any(Photo.class))).thenReturn(responseDTO);

        PhotoResponseDTO result = service.upload(file, "Cachoeira do Frade", 1);

        assertThat(result).isNotNull();
        verify(validationService).validateImage(file);
        verify(storageService).upload(file);
        verify(repository).save(any(Photo.class));
    }

    @Test
    void upload_WhenInvalidFile_ThrowsException() throws IOException {
        doThrow(new IllegalArgumentException("Tipo de arquivo não permitido"))
                .when(validationService).validateImage(file);

        assertThatThrownBy(() -> service.upload(file, "desc", 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Tipo de arquivo não permitido");

        verify(storageService, never()).upload(any());
        verify(repository, never()).save(any());
    }

    @Test
    void delete_WhenExists_DeletesFromS3AndDatabase() {
        when(repository.findById(id)).thenReturn(Optional.of(photo));
        service.delete(id);
        verify(storageService).delete("uuid-foto.jpg");
        verify(repository).delete(photo);
    }

    @Test
    void delete_WhenNotExists_ThrowsResourceNotFoundException() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.delete(id))
                .isInstanceOf(ResourceNotFoundException.class);
        verify(storageService, never()).delete(any());
    }
}