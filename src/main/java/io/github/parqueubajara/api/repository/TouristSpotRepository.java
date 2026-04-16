package io.github.parqueubajara.api.repository;

import io.github.parqueubajara.api.model.TouristSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TouristSpotRepository extends JpaRepository<TouristSpot, UUID> {
}
