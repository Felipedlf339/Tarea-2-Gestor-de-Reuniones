package com.reuniones;

import java.time.Duration;
import java.time.LocalTime;
import java.time.LocalDate;

public class ReunionVirtual extends Reunion
{
    private String enlace;

    public ReunionVirtual(LocalDate fecha, LocalTime horaPrevista, Duration duracionPrevista, TipoReunion tipo, Empleado organizador, String enlace) throws EnlaceInvalidoException
    {

        super(fecha, horaPrevista, duracionPrevista, tipo, organizador);
        if (enlace == null || enlace.trim().isEmpty()) {
            throw new EnlaceInvalidoException("El enlace debe adjuntarse, no puede quedar en blanco.");
        }
        this.enlace = enlace;
    }

    public String getEnlace()
    {
        return enlace;
    }

    public void setEnlace(String enlace) throws EnlaceInvalidoException
    {
        if (enlace == null || enlace.trim().isEmpty()) {
            throw new EnlaceInvalidoException("El enlace debe adjuntarse, no puede quedar en blanco.");
        }
        this.enlace = enlace;
    }

    @Override
    public String toString()
    {
        return super.toString() + "\nEnlace: " + enlace;
    }
}