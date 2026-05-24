package com.reuniones;

import java.time.Duration;
import java.time.LocalTime;
import java.time.LocalDate;

/**
 * Representa una reunión presencial que se desarrolla en una sala.
 */
public class ReunionPresencial extends Reunion
{
    private String sala;

    /**
     *  Constructor de la clase ReunionPresencial.
     * @param fecha fecha programada de la reunión.
     * @param horaPrevista hora programada de la reunión.
     * @param duracionPrevista duración estimada de la reunión.
     * @param tipo motivo de la reunión.
     * @param organizador el empleado encargado de organizarla.
     * @param sala lugar físico donde se llevará a cabo la reunión.
     * @throws SalaInvalidaException si el texto es nulo, vacío o espacios en blanco.
     */
    public ReunionPresencial(LocalDate fecha, LocalTime horaPrevista, Duration duracionPrevista, TipoReunion tipo, Empleado organizador, String sala) throws SalaInvalidaException
    {
        super(fecha, horaPrevista, duracionPrevista, tipo, organizador);
        if (sala == null || sala.trim().isEmpty()) {
            throw new SalaInvalidaException("La sala de la reunión debe ser indicada, no puede quedar en blanco.");
        }
        this.sala = sala;
    }

    /**
     * Obtiene el nombre del espacio fisico de la reunión.
     * @return un string con el nombre de la sala.
     */
    public String getSala()
    {
        return sala;
    }

    /**
     * Para modificar el punto de reunión.
     * @param sala lugar físico donde se llevará a cabo la reunión.
     * @throws SalaInvalidaException si el texto es nulo, vacío o espacios en blanco.
     */
    public void setSala(String sala) throws SalaInvalidaException
    {
        if (sala == null || sala.trim().isEmpty()) {
            throw new SalaInvalidaException("La sala de la reunión debe ser indicada, no puede quedar en blanco.");
        }
        this.sala = sala;
    }

    /**
     * Representación en formato de texto.
     * @return los detalles específicos de la reunión (viene de la clase padre) y se agrega la sala.
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nSala: " + sala;
    }
}