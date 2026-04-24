package io.github.parqueubajara.api.repository;

import io.github.parqueubajara.api.model.TourGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TourGuideRepository extends JpaRepository<TourGuide, UUID> {

    boolean existsByEmail(String email);
}
