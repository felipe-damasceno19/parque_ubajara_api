package io.github.parqueubajara.api.repository;

import io.github.parqueubajara.api.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, UUID> {

    Photo findByStorageKey(String storageKey);
}
