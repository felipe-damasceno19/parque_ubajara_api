package io.github.parqueubajara.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    // Este arquivo habilita o preenchimento automático das datas de auditoria
}