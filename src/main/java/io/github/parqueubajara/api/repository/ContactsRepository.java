package io.github.parqueubajara.api.repository;

import io.github.parqueubajara.api.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, UUID> {

    boolean existsByEmail(String email);
}
