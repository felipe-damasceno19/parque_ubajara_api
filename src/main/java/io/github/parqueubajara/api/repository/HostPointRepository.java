package io.github.parqueubajara.api.repository;

import io.github.parqueubajara.api.model.HostPoint;
import io.github.parqueubajara.api.model.enums.HostType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HostPointRepository extends JpaRepository<HostPoint, UUID> {

    Page<HostPoint> findByHostType(HostType hostType, Pageable pageable);
}
