package io.github.parqueubajara.api.config;

import io.github.parqueubajara.api.model.SystemUser;
import io.github.parqueubajara.api.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing // Ativa o mecanismo de auditoria
@RequiredArgsConstructor
public class JpaConfig {

    private final SecurityService securityService;

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            SystemUser user = securityService.getUserLogged();

            if (user == null) {
                return Optional.of("SISTEMA");
            }

            String name = user.getFirstName() + " " +user.getLastName();

            return Optional.ofNullable(name.trim());
        };
    }
}