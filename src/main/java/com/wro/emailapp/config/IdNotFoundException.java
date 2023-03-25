package com.wro.emailapp.config;

import java.util.UUID;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(UUID id) {
        super(id + " not found!");
    }
}
