package com.reuniones;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Reunion {
    private Date fecha;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio;
    private Instant horaFin;
    private TipoReunion tipo;
    private Empleado organizador;
    private List<Invitacion> invitaciones;
    private List<Asistencia> asistencias;
    private List<Nota> notas;
    private List<Retraso> retrasos;

    public Reunion(Date fecha, Instant horaPrevista, Duration duracionPrevista, TipoReunion tipo, Empleado organizador) {
        this.fecha = fecha;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = duracionPrevista;
        this.tipo = tipo;
        this.organizador = organizador;
        this.invitaciones = new ArrayList<>();
        this.asistencias = new ArrayList<>();
        this.notas = new ArrayList<>();
        this.retrasos = new ArrayList<>();
    }

    public void iniciar() {
        this.horaInicio = Instant.now();
    }

    public void finalizar() {
        this.horaFin = Instant.now();
    }

    public float calcularTiempoReal() {
        if (horaInicio == null || horaFin == null) {
            return 0;
        }
        return Duration.between(horaInicio, horaFin).toMinutes();
    }

    public List<Asistencia> obtenerAsistencias() {
        return asistencias;
    }

    public List<Invitacion> obtenerAusencias() {
        List<Invitacion> ausentes = new ArrayList<>();

        for (Invitacion i : invitaciones) {
            Invitable invitado = i.getInvitado();
            boolean asistio = false;

            for (Asistencia a : asistencias) {
                if (a.getParticipante().getCorreo().equals(invitado.getCorreo())) {
                    asistio = true;
                    break;
                }
            }

            if (!asistio) {
                ausentes.add(i);
            }
        }
        return ausentes;
}

    public List<Retraso> obtenerRetrasos() {
        return retrasos;
    }

    public int obtenerTotalAsistencia() {
    return asistencias.size();
}

    public float obtenerPorcentajeAsistencia() {
        if (invitaciones.isEmpty())
        {
            return 0.0f;
        }
        return (float) asistencias.size() / invitaciones.size() * 100;
    }

    public void agregarInvitacion(Invitacion invitacion)
    {
        invitaciones.add(invitacion);
        invitacion.getInvitado().invitar();
    }

    public void invitarDepartamento(Departamento departamento)
    {
        if (departamento != null)
        {
            for (Empleado e : departamento.getEmpleados())
            {
                Invitacion nuevaInvitacion = new Invitacion(e);
                this.agregarInvitacion(nuevaInvitacion);
            }
        }
    }

    public void agregarAsistencia(Invitable participante)
    {
        asistencias.add(new Asistencia(participante));
    }

    public void agregarRetraso(Retraso retraso) {
        retrasos.add(retraso);
        asistencias.add(new Asistencia(retraso.getParticipante()));
    }



    public void nuevaNota(Nota nota) {
        notas.add(nota);

    }

    public Date getFecha() {
        return fecha;
    }
    public Instant getHoraPrevista() {
        return horaPrevista;
    }
    public Duration getDuracionPrevista() {
        return duracionPrevista;
    }
    public Instant getHoraInicio() {
        return horaInicio;
    }
    public Instant getHoraFin() {
        return horaFin;
    }
    public TipoReunion getTipo() {
        return tipo;
    }
    public Empleado getOrganizador() {
        return organizador;
    }
    public List<Invitacion> getInvitaciones() {
        return invitaciones;
    }
    public List<Nota> getNotas() {
        return notas;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public void setHoraPrevista(Instant horaPrevista) {
        this.horaPrevista = horaPrevista;
    }
    public void setDuracionPrevista(Duration duracionPrevista) {
        this.duracionPrevista = duracionPrevista;
    }
    public void setTipo(TipoReunion tipo) {
        this.tipo = tipo;
    }
    public void setOrganizador(Empleado organizador) {
        this.organizador = organizador;
    }

    @Override
    public String toString()
    {
        if (organizador == null)
        {
            return "Reunion tipo: " + tipo + "\n" +
                    "Fecha: " + fecha + "\n" +
                    "Hora: " + horaPrevista + "\n" +
                    "Invitados: " + invitaciones.size();
        }
        else
        {
            return "Reunion tipo: " + tipo + "\n" +
                    "Fecha: " + fecha + "\n" +
                    "Hora: " + horaPrevista + "\n" +
                    "Organizador: " + organizador.getNombre() + " " + organizador.getApellidos() + "\n" +
                    "Invitados: " + invitaciones.size();
        }
    }
}