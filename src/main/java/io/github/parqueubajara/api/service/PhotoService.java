package io.github.parqueubajara.api.service;

import io.github.parqueubajara.api.dto.response.PhotoResponseDTO;
import io.github.parqueubajara.api.mapper.PhotoMapper;
import io.github.parqueubajara.api.model.Photo;
import io.github.parqueubajara.api.repository.PhotoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository repository;
    private final S3StorageService storageService;
    private final PhotoMapper mapper;

    @Transactional(readOnly = true)
    public Optional<Photo> findByIdOptional(UUID id){
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Photo findById(UUID id){
        return findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException("Photo de ID: "+ id + " não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<PhotoResponseDTO> findAll(){
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public PhotoResponseDTO upload(MultipartFile file, String description,
                                    Integer displayOrder) throws IOException {

        String url = storageService.upload(file);
        String storageKey = extractStorageKey(url);

        Photo photo = instantiatePhotoTemplate(description, url, storageKey, displayOrder);
        repository.save(photo);

        return mapper.toResponseDTO(photo);
    }

    @Transactional
    public void delete(UUID id){
        Photo photo = findById(id);
        storageService.delete(photo.getStorageKey());
        repository.delete(photo);
    }

    private Photo instantiatePhotoTemplate(String description, String url
            , String storageKey, Integer displayOrder){
        Photo photo = new Photo();
        photo.setDescription(description);
        photo.setUrl(url);
        photo.setStorageKey(storageKey);
        photo.setDisplayOrder(displayOrder);

        return photo;
    }

    private String extractStorageKey(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

}
