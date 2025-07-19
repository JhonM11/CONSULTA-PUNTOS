package com.consultapuntos.puntos.Exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String msg) {
        super(msg);
    }
}