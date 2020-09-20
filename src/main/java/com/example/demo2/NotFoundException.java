package com.example.demo2;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Could not find record " + id);
    }
}
