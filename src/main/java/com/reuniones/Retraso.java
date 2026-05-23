package com.reuniones;
import java.time.Instant;

/**
 * Representa un registro de asistencia con retraso a una reunión
 * Esta clase hereda de Asistencia, incorporando la hora exacta de la llegada
 */
public class Retraso extends Asistencia {

    private Instant hora;

    /**
     * Constructor de la clase Retraso
     * Crea un registro de asistencia tardía para un participante
     * @param participante El participante que llegó tarde
     * @param hora         El instante exacto en el que el participante se unió a la reunión
     */
    public Retraso(Invitable participante, Instant hora) {
        super(participante);
        this.hora = hora;
    }

    /**
     * Obtiene la hora a la que llegó el participante atrasado
     * @return Un objeto Instant con la hora
     */
    public Instant getHora() {
        return hora;
    }

    /**
     * Modifica la hora de llegada del participante
     * @param hora La nueva hora de llegada
     */
    public void setHora(Instant hora) {
        this.hora = hora;
    }

    /**
     * Genera una representación en texto del retraso
     * Utiliza el método toString() de la clase padre
     * @return Una cadena de texto indicando el participante y su hora de llegada
     */
    @Override
    public String toString() {
        return super.toString() + " || Llegada con retraso a las: " + hora.toString();
    }
}