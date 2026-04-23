package io.github.parqueubajara.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileValidationService {

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    private static final List<String> ALLOWED_IMAGE_TYPES = List.of(
            "image/jpeg",
            "image/png",
            "image/webp",
            "image/gif"
    );

    public void validateImage(MultipartFile file) {
        validateSize(file);
        validateImageType(file);
    }

    private void validateSize(MultipartFile file){
        if(file.isEmpty()){
            throw new IllegalArgumentException("O arquivo não pode estar vazio");
        }

        if(file.getSize() > MAX_FILE_SIZE){
            throw new IllegalArgumentException("O arquivo excede o tamanho permitido");
        }
    }

    private void validateImageType(MultipartFile file){
        if(!ALLOWED_IMAGE_TYPES.contains(file.getContentType())){
            throw new IllegalArgumentException("Tipo de arquivo não permitido. Permitidos: JPEG, PNG, WEBP, GIF");
        }
    }
}
