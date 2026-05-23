package com.reuniones;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class ReunionPresencial extends Reunion
{
    private String sala;

    public ReunionPresencial(Date fecha, Instant horaPrevista, Duration duracionPrevista, TipoReunion tipo, Empleado organizador, String sala)
    {
        super(fecha, horaPrevista, duracionPrevista, tipo, organizador);
        this.sala = sala;
    }

    public String getSala()
    {
        return sala;
    }

    public void setSala(String sala)
    {
        this.sala = sala;
    }

    @Override
    public String toString()
    {
        return super.toString() + "\nSala: " + sala;
    }
}