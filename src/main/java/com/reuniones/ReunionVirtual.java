package com.reuniones;

import java.time.Duration;
import java.time.LocalTime;
import java.time.LocalDate;

/**
 * Representa una reunión virtual que se desarrollará en una plataforma de reuniones virtual.
 */
public class ReunionVirtual extends Reunion
{
    private String enlace;

    /**
     * Constructor de la clase ReunionVirtual.
     * @param fecha fecha programada de la reunión.
     * @param horaPrevista hora programada de la reunión.
     * @param duracionPrevista duración estimada de la reunión.
     * @param tipo motivo de la reunión.
     * @param organizador el empleado encargado de organizarla.
     * @param enlace enlace para conectarse a la plataforma.
     * @throws EnlaceInvalidoException si el texto es nulo, vacío o espacios en blanco.
     */
    public ReunionVirtual(LocalDate fecha, LocalTime horaPrevista, Duration duracionPrevista, TipoReunion tipo, Empleado organizador, String enlace) throws EnlaceInvalidoException
    {

        super(fecha, horaPrevista, duracionPrevista, tipo, organizador);
        if (enlace == null || enlace.trim().isEmpty()) {
            throw new EnlaceInvalidoException("El enlace debe adjuntarse, no puede quedar en blanco.");
        }
        this.enlace = enlace;
    }

    /**
     * Obtiene el enlace de la reunión.
     * @return un string con el enlace.
     */
    public String getEnlace()
    {
        return enlace;
    }

    /**
     * Para modificar el enlace adjuntado.
     * @param enlace enlace para conectarse a la plataforma.
     * @throws EnlaceInvalidoException si el texto es nulo, vacío o espacios en blanco.
     */
    public void setEnlace(String enlace) throws EnlaceInvalidoException
    {
        if (enlace == null || enlace.trim().isEmpty()) {
            throw new EnlaceInvalidoException("El enlace debe adjuntarse, no puede quedar en blanco.");
        }
        this.enlace = enlace;
    }

    /**
     * Representación en formato de texto.
     * @return los detalles específicos de la reunión (viene de la clase padre) y se agrega la sala.
     */
    @Override
    public String toString()
    {
        return super.toString() + "\nEnlace: " + enlace;
    }
}