package com.reuniones;

/**
 * Excepción lanzada en caso de que se intente crear un empleado sin correo válido.
 */
public class EmpleadoSinCorreoException extends Exception {
    public EmpleadoSinCorreoException(String mensaje) {
        super(mensaje);
    }
}
