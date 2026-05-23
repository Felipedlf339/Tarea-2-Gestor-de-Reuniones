package com.reuniones;

/**
 * Interfaz que representa cualquier entidad que puede ser invitada a una reunión.
 */

public interface Invitable {
    /**
     * Envía una invitación a la entidad.
     */
    void invitar();

    /**
     * Obtiene el correo del invitado para revisar su asistencia.
     */
    String getCorreo();
}
