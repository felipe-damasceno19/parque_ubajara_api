package io.github.parqueubajara.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    String upload(MultipartFile file) throws IOException;
    void delete(String storageKey);
}
