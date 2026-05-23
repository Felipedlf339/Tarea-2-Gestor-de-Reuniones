package com.reuniones;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotaTest {

    @Test
    public void testCreacionNotaNormal() {
        assertDoesNotThrow(() -> {
            Nota nota = new Nota("Llamar a la DOH.");
            assertEquals("Llamar a la DOH.", nota.getContenido());
        });
    }

    @Test
    public void testSetContenidoNormal() {
        assertDoesNotThrow(() -> {
            Nota nota = new Nota("Coordinar futura reunión con el alcalde y la directora.");
            nota.setContenido("Reunión con el alcalde y directora fijada.");
            assertEquals("Reunión con el alcalde y directora fijada.", nota.getContenido());
        });
    }

    @Test
    public void testToStringDevuelveContenidoCorrecto() {
        assertDoesNotThrow(() -> {
            Nota nota = new Nota("Visitar sector Chacayal sur.");
            assertEquals("Visitar sector Chacayal sur.", nota.toString());
        });
    }

    @Test
    public void testCrearNotaVaciaLanzaExcepcion() {
        assertThrows(NotaInvalidaException.class, () -> {
            new Nota("");
        });
    }

    @Test
    public void testCrearNotaNulaLanzaExcepcion() {
        assertThrows(NotaInvalidaException.class, () -> {
            new Nota(null);
        });
    }

    @Test
    public void testCrearNotaConEspaciosLanzaExcepcion() {
        assertThrows(NotaInvalidaException.class, () -> {
            new Nota("      ");
        });
    }

    @Test
    public void testSetearNotaVaciaLanzaExcepcion() {
        assertDoesNotThrow(() -> {
            Nota nota = new Nota("Reunión en el GORE Biobío el 30 de junio..");

            assertThrows(NotaInvalidaException.class, () -> {
                nota.setContenido("");
            });
        });
    }
}