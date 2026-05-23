package com.reuniones;

/**
 * Excepción lanzada en caso de intentar crear un departamento sin nombre válido.
 */
public class DepartamentoSinNombreException extends Exception {
    public DepartamentoSinNombreException(String mensaje) {
        super(mensaje);
    }
}
