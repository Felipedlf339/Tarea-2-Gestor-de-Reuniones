package com.reuniones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Instant;
import java.util.List;

public class ReunionTest {

    private Reunion reunion;
    private Empleado organizador;
    private Empleado invitado1;
    private Empleado invitado2;
    private LocalDate fecha;
    private LocalTime hora;
    private Duration duracion;

    @BeforeEach
    public void setUp() throws Exception {
        organizador = new Empleado("E001", "Javiera", "Aravena", "jaravena2025@inf.com");
        invitado1 = new Empleado("E002", "Martín", "García", "margarcia2025@inf.com");
        invitado2 = new Empleado("E003", "Felipe", "De la Fuente", "fdelafuente@inf.com");

        fecha = LocalDate.now();
        hora = LocalTime.now();
        duracion = Duration.ofHours(1);

        reunion = new Reunion(fecha, hora, duracion, TipoReunion.TECNICA, organizador) {};
    }

    @Test
    public void testAtributosBaseSeGuardanCorrectamente() {
        assertDoesNotThrow(() -> {
            assertEquals(fecha, reunion.getFecha());
            assertEquals(hora, reunion.getHoraPrevista());
            assertEquals(duracion, reunion.getDuracionPrevista());
            assertEquals(TipoReunion.TECNICA, reunion.getTipo());
            assertEquals(organizador, reunion.getOrganizador());
            assertNotNull(reunion.getInvitaciones());
            assertTrue(reunion.getInvitaciones().isEmpty());
            assertNotNull(reunion.getNotas());
            assertTrue(reunion.getNotas().isEmpty());
        });
    }

    @Test
    public void testIniciarReunionGuardaHoraInicio() {
        assertDoesNotThrow(() -> {
            assertNull(reunion.getHoraInicio());
            reunion.iniciar();
            assertNotNull(reunion.getHoraInicio());
        });
    }

    @Test
    public void testIniciarReunionYaIniciadaLanzaExcepcion() {
        assertDoesNotThrow(() -> reunion.iniciar());

        ReunionException ex = assertThrows(ReunionException.class, () -> reunion.iniciar());
        assertEquals("La reunión ya ha comenzado.", ex.getMessage());
    }

    @Test
    public void testFinalizarReunionSinIniciarLanzaExcepcion() {
        ReunionException ex = assertThrows(ReunionException.class, () -> reunion.finalizar());
        assertEquals("No se puede finalizar si no ha comenzado.", ex.getMessage());
    }

    @Test
    public void testFinalizarReunionYaFinalizadaLanzaExcepcion() {
        assertDoesNotThrow(() -> {
            reunion.iniciar();
            reunion.finalizar();
        });

        ReunionException ex = assertThrows(ReunionException.class, () -> reunion.finalizar());
        assertEquals("La reunión ya se ha finalizado.", ex.getMessage());
    }

    @Test
    public void testCalcularTiempoRealSinFinalizarLanzaExcepcion() {
        assertDoesNotThrow(() -> reunion.iniciar());

        ReunionException ex = assertThrows(ReunionException.class, () -> reunion.calcularTiempoReal());
        assertEquals("La reunión no ha empezado o terminado.", ex.getMessage());
    }

    @Test
    public void testAgregarInvitacionExitosa() {
        assertDoesNotThrow(() -> {
            reunion.agregarInvitacion(new Invitacion(invitado1));
            assertEquals(1, reunion.getInvitaciones().size());
        });
    }

    @Test
    public void testAgregarInvitacionDuplicadaLanzaExcepcion() {
        assertDoesNotThrow(() -> reunion.agregarInvitacion(new Invitacion(invitado1)));

        ReunionException ex = assertThrows(ReunionException.class, () -> {
            reunion.agregarInvitacion(new Invitacion(invitado1));
        });
        assertEquals("Ya se ha enviado una invitación.", ex.getMessage());
    }

    @Test
    public void testAgregarInvitacionReunionFinalizadaLanzaExcepcion() {
        assertDoesNotThrow(() -> {
            reunion.iniciar();
            reunion.finalizar();
        });

        ReunionException ex = assertThrows(ReunionException.class, () -> {
            reunion.agregarInvitacion(new Invitacion(invitado1));
        });
        assertEquals("No se pueden enviar invitaciones si la reunión ya finalizó.", ex.getMessage());
    }

    @Test
    public void testInvitarDepartamentoAgregaMultiplesInvitaciones() {
        assertDoesNotThrow(() -> {
            Departamento dep = new Departamento("datascience", "datasc@info.com");
            dep.agregarEmpleado(invitado1);
            dep.agregarEmpleado(invitado2);

            reunion.invitarDepartamento(dep);
            assertEquals(2, reunion.getInvitaciones().size());
        });
    }

    @Test
    public void testAgregarAsistenciaReunionNoIniciadaLanzaExcepcion() {
        ReunionException ex = assertThrows(ReunionException.class, () -> reunion.agregarAsistencia(invitado1));
        assertEquals("No se puede anotar la asistencia si la reunión no ha comenzado.", ex.getMessage());
    }

    @Test
    public void testAgregarAsistenciaReunionFinalizadaLanzaExcepcion() {
        assertDoesNotThrow(() -> {
            reunion.iniciar();
            reunion.finalizar();
        });

        ReunionException ex = assertThrows(ReunionException.class, () -> reunion.agregarAsistencia(invitado1));
        assertEquals("No se puede anotar la asistencia si la reunión ya finalizó.", ex.getMessage());
    }

    @Test
    public void testAgregarAsistenciaDuplicadaLanzaExcepcion() {
        assertDoesNotThrow(() -> {
            reunion.iniciar();
            reunion.agregarAsistencia(invitado1);
        });

        ReunionException ex = assertThrows(ReunionException.class, () -> reunion.agregarAsistencia(invitado1));
        assertEquals("El participante ya esta anotado.", ex.getMessage());
    }

    @Test
    public void testAgregarRetrasoSumaAsistenciaAutomaticamente() {
        assertDoesNotThrow(() -> {
            reunion.iniciar();
            Retraso retraso = new Retraso(invitado1, Instant.now());
            reunion.agregarRetraso(retraso);

            assertEquals(1, reunion.obtenerRetrasos().size());
            assertEquals(1, reunion.obtenerTotalAsistencia());
        });
    }

    @Test
    public void testPorcentajeAsistenciaCeroSinInvitados() {
        assertEquals(0.0f, reunion.obtenerPorcentajeAsistencia());
    }

    @Test
    public void testCalculoCorrectoPorcentajeAsistencia() {
        assertDoesNotThrow(() -> {
            reunion.agregarInvitacion(new Invitacion(invitado1));
            reunion.agregarInvitacion(new Invitacion(invitado2));

            reunion.iniciar();
            reunion.agregarAsistencia(invitado1);

            assertEquals(50.0f, reunion.obtenerPorcentajeAsistencia());
        });
    }

    @Test
    public void testObtenerAusenciasIndicaCorrectamente() {
        assertDoesNotThrow(() -> {
            reunion.agregarInvitacion(new Invitacion(invitado1));
            reunion.agregarInvitacion(new Invitacion(invitado2));

            reunion.iniciar();
            reunion.agregarAsistencia(invitado1);

            List<Invitacion> ausentes = reunion.obtenerAusencias();
            assertEquals(1, ausentes.size());
            assertEquals("fdelafuente@inf.com", ausentes.get(0).getInvitado().getCorreo());
        });
    }

}