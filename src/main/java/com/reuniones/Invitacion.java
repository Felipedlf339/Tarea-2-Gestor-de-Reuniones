package com.reuniones;
import java.time.Instant;

/**
 * Representa una invitación enviada a un participante para una reunión
 */
public class Invitacion {

    private Instant hora;
    private Invitable invitado;

    /**
     * Constructor de la clase Invitacion.
     * @param invitado La persona que está siendo invitada
     */
    public Invitacion(Invitable invitado) {
        this.hora = Instant.now();
        this.invitado = invitado;
    }

    /**
     * Obtiene la hora en la que se generó la invitación
     * @return El instante de la invitación.
     */
    public Instant getHora() {
        return hora;
    }

    /**
     * Establece una nueva hora para la invitación
     * @param hora La nueva hora de la invitación
     */
    public void setHora(Instant hora) {
        this.hora = hora;
    }

    /**
     * Obtiene el participante al que va dirigida esta invitación.
     * @return El objeto Invitable, ya sea un empleado o un invitado externo
     */
    public Invitable getInvitado() {
        return invitado;
    }

    /**
     * Asigna o modifica el invitado de esta invitación
     * @param invitado El nuevo objeto Invitable al que se le enviará la invitación
     */
    public void setInvitado(Invitable invitado) {
        this.invitado = invitado;
    }

    /**
     * Genera una representación en texto de la invitación
     * @return La cadena de texto con el nombre del invitado y con la hora de emisión
     */
    @Override
    public String toString() {
        return "Invitación para: " + invitado.toString() + " || Invitación emitida a las: " + hora.toString();
    }
}