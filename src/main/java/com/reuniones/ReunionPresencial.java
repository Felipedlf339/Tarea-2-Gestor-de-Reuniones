package com.reuniones;

import java.time.Duration;
import java.time.LocalTime;
import java.time.LocalDate;

public class ReunionPresencial extends Reunion
{
    private String sala;

    public ReunionPresencial(LocalDate fecha, LocalTime horaPrevista, Duration duracionPrevista, TipoReunion tipo, Empleado organizador, String sala) throws SalaInvalidaException
    {
        super(fecha, horaPrevista, duracionPrevista, tipo, organizador);
        if (sala == null || sala.trim().isEmpty()) {
            throw new SalaInvalidaException("La sala de la reunión debe ser indicada, no puede quedar en blanco.");
        }
        this.sala = sala;
    }

    public String getSala()
    {
        return sala;
    }

    public void setSala(String sala) throws SalaInvalidaException
    {
        if (sala == null || sala.trim().isEmpty()) {
            throw new SalaInvalidaException("La sala de la reunión debe ser indicada, no puede quedar en blanco.");
        }
        this.sala = sala;
    }

    @Override
    public String toString()
    {
        return super.toString() + "\nSala: " + sala;
    }
}