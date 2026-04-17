package io.github.parqueubajara.api.repository;

import io.github.parqueubajara.api.model.HostPoint;
import io.github.parqueubajara.api.model.enums.HostType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HostPointRepository extends JpaRepository<HostPoint, UUID> {

    HostPoint findByHostType(HostType hostType);
}
