package com.reuniones;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class ReunionVirtual extends Reunion
{
    private String enlace;

    public ReunionVirtual(Date fecha, Instant horaPrevista, Duration duracionPrevista, TipoReunion tipo, Empleado organizador, String enlace)
    {

        super(fecha, horaPrevista, duracionPrevista, tipo, organizador);
        this.enlace = enlace;
    }

    public String getEnlace()
    {
        return enlace;
    }

    public void setEnlace(String enlace)
    {
        this.enlace = enlace;
    }

    @Override
    public String toString()
    {
        return super.toString() + "\nEnlace: " + enlace;
    }
}