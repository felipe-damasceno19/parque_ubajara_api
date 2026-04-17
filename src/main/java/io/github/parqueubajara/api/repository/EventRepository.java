package io.github.parqueubajara.api.repository;

import io.github.parqueubajara.api.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    Event findByCreateDateBetween(LocalDateTime start, LocalDateTime end);
}
