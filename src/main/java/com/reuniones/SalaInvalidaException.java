package com.reuniones;

public class SalaInvalidaException extends Exception {
    public SalaInvalidaException(String mensaje) {
        super(mensaje);
    }
}