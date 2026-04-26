package io.github.parqueubajara.api.exception;

import jakarta.persistence.EntityNotFoundException;

public class ResourceNotFoundException extends EntityNotFoundException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
