package com.reuniones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReunionVirtualTest {

    private Empleado organizador;
    private LocalDate fecha;
    private LocalTime hora;
    private Duration duracion;

    @BeforeEach
    public void setUp() throws Exception {
        organizador = new Empleado("E004", "Andrea", "Gutierrez", "agutierrez@empresa.com");
        fecha = LocalDate.now();
        hora = LocalTime.now();
        duracion = Duration.ofHours(1);
    }

    @Test
    public void testCreacionReunionVirtualNormal() {
        assertDoesNotThrow(() -> {
            ReunionVirtual reunion = new ReunionVirtual(fecha, hora, duracion, TipoReunion.TECNICA, organizador, "https://meet.google.com/jpag");
            assertEquals("https://meet.google.com/jpag", reunion.getEnlace());
        });
    }

    @Test
    public void testSetEnlaceNormal() {
        assertDoesNotThrow(() -> {
            ReunionVirtual reunion = new ReunionVirtual(fecha, hora, duracion, TipoReunion.TECNICA, organizador, "https://meet.google.com/yrmw");
            reunion.setEnlace("https://meet.google.com/143");
            assertEquals("https://meet.google.com/143", reunion.getEnlace());
        });
    }

    @Test
    public void testToStringContieneEnlace() {
        assertDoesNotThrow(() -> {
            ReunionVirtual reunion = new ReunionVirtual(fecha, hora, duracion, TipoReunion.TECNICA, organizador, "https://meet.google.com/jvvi");
            assertTrue(reunion.toString().contains("https://meet.google.com/jvvi"));
        });
    }

    @Test
    public void testCrearReunionEnlaceVacioLanzaExcepcion() {
        assertThrows(EnlaceInvalidoException.class, () -> {
            new ReunionVirtual(fecha, hora, duracion, TipoReunion.TECNICA, organizador, "");
        });
    }

    @Test
    public void testCrearReunionEnlaceNuloLanzaExcepcion() {
        assertThrows(EnlaceInvalidoException.class, () -> {
            new ReunionVirtual(fecha, hora, duracion, TipoReunion.TECNICA, organizador, null);
        });
    }

    @Test
    public void testCrearReunionEnlaceEspaciosLanzaExcepcion() {
        assertThrows(EnlaceInvalidoException.class, () -> {
            new ReunionVirtual(fecha, hora, duracion, TipoReunion.TECNICA, organizador, "   ");
        });
    }

    @Test
    public void testSetEnlaceVacioLanzaExcepcion() {
        assertDoesNotThrow(() -> {
            ReunionVirtual reunion = new ReunionVirtual(fecha, hora, duracion, TipoReunion.TECNICA, organizador, "https://zoom.us/j/123");

            assertThrows(EnlaceInvalidoException.class, () -> {
                reunion.setEnlace("");
            });
        });
    }
}