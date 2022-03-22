package com.spring.EmailAppSpring.config;

import java.util.UUID;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException() {
        super("Id not found!");
    }

}
