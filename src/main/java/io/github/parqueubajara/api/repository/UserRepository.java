package io.github.parqueubajara.api.repository;

import io.github.parqueubajara.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UUID, User> {
    
    User findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsById(UUID id);

}
