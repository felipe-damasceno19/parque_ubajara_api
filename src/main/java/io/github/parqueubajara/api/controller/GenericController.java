package io.github.parqueubajara.api.controller;


import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public interface GenericController {

    default URI generateHeaderLocation(UUID id) {
        if(id == null){
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
