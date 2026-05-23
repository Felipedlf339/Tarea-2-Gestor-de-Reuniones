package com.reuniones;

/**
 * Representa el registro de asistencia de un empleado a una reunión
 * Esta clase sirve para confirmar que un participante estuvo presente
 */
public class Asistencia {

    private Invitable participante;

    /**
     * Constructor de la clase Asistencia
     * Crea un nuevo registro de presencia para un invitado específico
     * @param participante El invitado que está registrando su asistencia a la reunión
     */
    public Asistencia(Invitable participante) {
        this.participante = participante;
    }

    /**
     * Obtiene el participante asociado a este registro de asistencia
     * @return El objeto Invitable que asistió a la reunión
     */
    public Invitable getParticipante() {
        return participante;
    }

    /**
     * Modifica o asigna el invitado asociado a este registro de asistencia
     * @param participante El nuevo objeto Invitable que registrará su asistencia
     */
    public void setParticipante(Invitable participante) {
        this.participante = participante;
    }

    /**
     * Genera una representación en texto del registro de asistencia
     * @return Una cadena de texto indicando la confirmación de asistencia y los datos del participante
     */
    @Override
    public String toString() {
        if (participante != null) {
            return "Asistencia confirmada de: " + participante.getNombre() + " " + participante.getApellidos();
        }
        return "Asistencia de invitado desconocido";
    }
}