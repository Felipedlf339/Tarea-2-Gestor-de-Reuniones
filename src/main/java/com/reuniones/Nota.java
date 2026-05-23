package com.reuniones;

public class Nota {
    private String contenido;

    public Nota(String contenido) throws NotaInvalidaException {
        if (contenido == null || contenido.trim().isEmpty()) {
            throw new NotaInvalidaException("La nota no puede estar en blanco");
        }
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) throws NotaInvalidaException {
        if (contenido == null || contenido.trim().isEmpty()) {
            throw new NotaInvalidaException("La nota no puede estar en blanco.");
        }
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return contenido;
    }
}