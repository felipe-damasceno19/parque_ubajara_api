package io.github.parqueubajara.api.repository;

import io.github.parqueubajara.api.model.Attractive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttractiveRepository extends JpaRepository<Attractive, UUID> {
}
