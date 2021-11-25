package com.example.library.exception;

public class NoDeletionException extends RuntimeException {

    public NoDeletionException(String message) {
        super(message);
    }
}
