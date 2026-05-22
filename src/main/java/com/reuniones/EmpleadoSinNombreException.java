package com.reuniones;

/**
 * Excepción lanzada en caso de intentar crear un empleado sin nombre.
 */
public class EmpleadoSinNombreException extends Exception {
    public EmpleadoSinNombreException(String mensaje) {
        super(mensaje);
    }
}
