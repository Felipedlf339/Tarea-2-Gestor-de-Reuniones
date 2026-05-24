package com.reuniones;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que representa una reunión.
 */
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

    /**
     * Constructor de la clase Reunion.
     * @param fecha fecha programada de la reunión.
     * @param horaPrevista hora programada de la reunión.
     * @param duracionPrevista duración estimada de la reunión.
     * @param tipo motivo de la reunión.
     * @param organizador el empleado encargado de organizarla.
     */
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

    /**
     * Da inicio a la reunión guardando la hora actual del sistema.
     * @throws ReunionException si intenta iniciar y la reunión ya comenzó.
     */
    public void iniciar() throws ReunionException {
        if (this.horaInicio != null)
        {
            throw new ReunionException("La reunión ya ha comenzado.");
        }
        this.horaInicio = Instant.now();
    }

    /**
     * Finaliza la reunión guardando la hora actual del sistema.
     * @throws ReunionException si intenta finalizar antes de que empezara o después de que terminara.
     */
    public void finalizar() throws ReunionException {
        if (this.horaInicio == null) {
            throw new ReunionException("No se puede finalizar si no ha comenzado.");
        }
        if (this.horaFin != null) {
            throw new ReunionException("La reunión ya se ha finalizado.");
        }
        this.horaFin = Instant.now();
    }

    /**
     * Calcula la duración de la reunión en minutos.
     * @return el tiempo de la reunión en minutos (float).
     * @throws ReunionException si se intenta calcular y no hay hora de inicio o fin.
     */
    public float calcularTiempoReal() throws ReunionException {
        if (horaInicio == null || horaFin == null) {
            throw new ReunionException("La reunión no ha empezado o terminado.");
        }
        return Duration.between(horaInicio, horaFin).toMinutes();
    }

    /**
     * Obtiene la lista de asistencia.
     * @return las asistencias registradas en una lista.
     */
    public List<Asistencia> obtenerAsistencias() {
        return asistencias;
    }

    /**
     * Calcula y a su vez obtiene la lista de invitados ausentados.
     * @return la lista de invitados ausentes.
     */
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

    /**
     * Obtiene la lista de atrasados registrados.
     * @return lista de atrasados a la reunión.
     */
    public List<Retraso> obtenerRetrasos() {
        return retrasos;
    }

    /**
     * Cantidad total de asistentes a la reunión.
     * @return entero con el número de asistentes.
     */
    public int obtenerTotalAsistencia() {
    return asistencias.size();
}

    /**
     * Calcula el porcentaje de asistencia respecto a las invitaciones.
     * @return  float con el porcentaje, 0.0 si no hay invitados.
     */
    public float obtenerPorcentajeAsistencia() {
        if (invitaciones.isEmpty())
        {
            return 0.0f;
        }
        return (float) asistencias.size() / invitaciones.size() * 100;
    }

    /**
     * Para invitar a nuevos participantes a la reunión.
     * @param invitacion la invitación que se procesará.
     * @throws ReunionException si se intenta hacer una invitación a alguien que ya esta invitado o si la reunión ya finalizo.
     */
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

    /**
     * Para invitar a un departamento completo.
     * @param departamento departamento que se esta invitando.
     * @throws ReunionException si ocurre un error al procesar las invitaciones.
     */
    public void invitarDepartamento(Departamento departamento) throws ReunionException
    {
        if (departamento != null) {
            for (Empleado e : departamento.getEmpleados()) {
                Invitacion nuevaInvitacion = new Invitacion(e);
                this.agregarInvitacion(nuevaInvitacion);
            }
        }
    }

    /**
     * Para anotar que un participante llego a la reunión.
     * @param participante la persona que llega a la reunión.
     * @throws ReunionException no se puede anotar la asistencia si no ha comenzado o si ya finalizo, tampoco se pueden anotar más de una vez.
     */
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

    /**
     * Para anotar a los que llegan atrasados y anotar su asistencia.
     * @param retraso el registro del retraso.
     * @throws ReunionException si la reunión no ha empezado o terminado.
     */
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

    /**
     * Añadir una nota de texto a la reunión.
     * @param nota nota que se va a añadir.
     */
    public void nuevaNota(Nota nota) {
        notas.add(nota);

    }

    // getters
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

    //setters
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

    /**
     * Representación en formato de texto con los datos de la reunión.
     * @return la información en formato string de la reunión.
     */
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