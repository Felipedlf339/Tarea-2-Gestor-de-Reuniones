package com.reuniones;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Reunion {
    private LocalDate fecha;
    private LocalTime horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio;
    private Instant horaFin;
    private TipoReunion tipo;
    private Empleado organizador;
    private List<Invitacion> invitaciones;
    private List<Asistencia> asistencias;
    private List<Nota> notas;
    private List<Retraso> retrasos;

    public Reunion(LocalDate fecha, LocalTime horaPrevista, Duration duracionPrevista, TipoReunion tipo, Empleado organizador) {
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

    public void iniciar() throws ReunionException {
        if (this.horaInicio != null)
        {
            throw new ReunionException("La reunión ya ha comenzado.");
        }
        this.horaInicio = Instant.now();
    }

    public void finalizar() throws ReunionException {
        if (this.horaInicio == null) {
            throw new ReunionException("No se puede finalizar si no ha comenzado.");
        }
        if (this.horaFin != null) {
            throw new ReunionException("La reunión ya se ha finalizado.");
        }
        this.horaFin = Instant.now();
    }

    public float calcularTiempoReal() throws ReunionException {
        if (horaInicio == null || horaFin == null) {
            throw new ReunionException("La reunión no ha empezado o terminado.");
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

    public void agregarInvitacion(Invitacion invitacion) throws ReunionException
    {
        if (this.horaFin != null) {
            throw new ReunionException("No se pueden enviar invitaciones si la reunión ya finalizó.");
        }
        for (Invitacion i : invitaciones) {
            if (i.getInvitado().getCorreo().equals(invitacion.getInvitado().getCorreo())) {
                throw new ReunionException("Ya se ha enviado una invitación.");
            }
        }
        invitaciones.add(invitacion);
        invitacion.getInvitado().invitar();
    }

    public void invitarDepartamento(Departamento departamento) throws ReunionException
    {
        if (departamento != null) {
            for (Empleado e : departamento.getEmpleados()) {
                Invitacion nuevaInvitacion = new Invitacion(e);
                this.agregarInvitacion(nuevaInvitacion);
            }
        }
    }

    public void agregarAsistencia(Invitable participante) throws ReunionException
    {
        if (this.horaInicio == null) {
            throw new ReunionException("No se puede anotar la asistencia si la reunión no ha comenzado.");
        }
        if (this.horaFin != null) {
            throw new ReunionException("No se puede anotar la asistencia si la reunión ya finalizó.");
        }
        for (Asistencia a : asistencias) {
            if (a.getParticipante().getCorreo().equals(participante.getCorreo())) {
                throw new ReunionException("El participante ya esta anotado.");
            }
        }
        asistencias.add(new Asistencia(participante));
    }

    public void agregarRetraso(Retraso retraso) throws ReunionException {
        if (this.horaInicio == null)
        {
            throw new ReunionException("La reunión no ha comenzado, no pueden haber retrasos.");
        }
        if (this.horaFin != null)
        {
            throw new ReunionException("La reunión ha finalizado, no pueden haber retrasos.");
        }
        retrasos.add(retraso);
        this.agregarAsistencia(retraso.getParticipante());
    }



    public void nuevaNota(Nota nota) {
        notas.add(nota);

    }

    public LocalDate getFecha() {
        return fecha;
    }
    public LocalTime getHoraPrevista() {
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

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setHoraPrevista(LocalTime horaPrevista) {
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