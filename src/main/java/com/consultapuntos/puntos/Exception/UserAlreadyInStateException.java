package com.consultapuntos.puntos.Exception;

public class UserAlreadyInStateException extends RuntimeException {
    public UserAlreadyInStateException(String message) {
        super(message);
    }
}