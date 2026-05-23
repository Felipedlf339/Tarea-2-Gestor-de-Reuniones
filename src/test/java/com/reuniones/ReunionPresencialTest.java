package com.reuniones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReunionPresencialTest {

    private Empleado organizador;
    private LocalDate fecha;
    private LocalTime hora;
    private Duration duracion;

    @BeforeEach
    public void setUp() throws Exception {
        organizador = new Empleado("E016", "Andrea", "Gutierrez", "agutierrez@empresa.com");
        fecha = LocalDate.now();
        hora = LocalTime.now();
        duracion = Duration.ofHours(2);
    }

    @Test
    public void testCreacionReunionPresencialNormal() {
        assertDoesNotThrow(() -> {
            ReunionPresencial reunion = new ReunionPresencial(fecha, hora, duracion, TipoReunion.TECNICA, organizador, "Sala de reuniones DOH");
            assertEquals("Sala de reuniones DOH", reunion.getSala());
        });
    }

    @Test
    public void testSetSalaNormal() {
        assertDoesNotThrow(() -> {
            ReunionPresencial reunion = new ReunionPresencial(fecha, hora, duracion, TipoReunion.TECNICA, organizador, "Sala A-213");
            reunion.setSala("Sala A-415");
            assertEquals("Sala A-415", reunion.getSala());
        });
    }

    @Test
    public void testToStringContieneSala() {
        assertDoesNotThrow(() -> {
            ReunionPresencial reunion = new ReunionPresencial(fecha, hora, duracion, TipoReunion.TECNICA, organizador, "Sede Social");
            assertTrue(reunion.toString().contains("Sede Social"));
        });
    }

    @Test
    public void testCrearReunionSalaVaciaLanzaExcepcion() {
        assertThrows(SalaInvalidaException.class, () -> {
            new ReunionPresencial(fecha, hora, duracion, TipoReunion.TECNICA, organizador, "");
        });
    }

    @Test
    public void testCrearReunionSalaNulaLanzaExcepcion() {
        assertThrows(SalaInvalidaException.class, () -> {
            new ReunionPresencial(fecha, hora, duracion, TipoReunion.TECNICA, organizador, null);
        });
    }

    @Test
    public void testCrearReunionSalaEspaciosLanzaExcepcion() {
        assertThrows(SalaInvalidaException.class, () -> {
            new ReunionPresencial(fecha, hora, duracion, TipoReunion.TECNICA, organizador, "   ");
        });
    }

    @Test
    public void testSetSalaVaciaLanzaExcepcion() {
        assertDoesNotThrow(() -> {
            ReunionPresencial reunion = new ReunionPresencial(fecha, hora, duracion, TipoReunion.TECNICA, organizador, "Sala Original");

            assertThrows(SalaInvalidaException.class, () -> {
                reunion.setSala("");
            });
        });
    }
}