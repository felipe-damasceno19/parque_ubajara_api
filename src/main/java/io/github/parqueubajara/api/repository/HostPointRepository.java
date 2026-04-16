package io.github.parqueubajara.api.repository;

import io.github.parqueubajara.api.model.HostPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HostPointRepository extends JpaRepository<HostPoint, UUID> {
}
