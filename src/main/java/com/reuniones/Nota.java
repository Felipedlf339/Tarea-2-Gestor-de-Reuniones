package com.reuniones;

/**
 * Representa una nota de texto.
 */
public class Nota {
    private String contenido;

    /**
     * Constructor de la clase Nota.
     * @param contenido contenido de la nota.
     * @throws NotaInvalidaException si el texto es nulo, vacío o espacios en blanco.
     */
    public Nota(String contenido) throws NotaInvalidaException {
        if (contenido == null || contenido.trim().isEmpty()) {
            throw new NotaInvalidaException("La nota no puede estar en blanco");
        }
        this.contenido = contenido;
    }

    /**
     * Obtiene el texto de la nota.
     * @return un string con el contenido de la nota.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Modifica el contenido de la nota por uno nuevo.
     * @param contenido contenido de la nota
     * @throws NotaInvalidaException si el texto es nulo, vacío o espacios en blanco.
     */
    public void setContenido(String contenido) throws NotaInvalidaException {
        if (contenido == null || contenido.trim().isEmpty()) {
            throw new NotaInvalidaException("La nota no puede estar en blanco.");
        }
        this.contenido = contenido;
    }

    /**
     * Representación en formato de texto.
     * @return un string con el contenido de la nota.
     */
    @Override
    public String toString() {
        return contenido;
    }
}