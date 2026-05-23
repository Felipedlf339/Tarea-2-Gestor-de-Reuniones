package com.reuniones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvitadoExternoTest {

    InvitadoExterno externo;

    @BeforeEach
    void setUp() {
        externo = new InvitadoExterno("Felipe de la Fuente", "felipe@gmail.com");
    }

    // Casos normales

    @Test
    void testNombreSeGuardaCorrectamente() {
        assertEquals("Felipe de la Fuente", externo.getNombreCompleto());
    }

    @Test
    void testCorreoSeGuardaCorrectamente() {
        assertEquals("felipe@gmail.com", externo.getCorreo());
    }

    @Test
    void testToString() {
        assertEquals("Externo: Felipe de la Fuente <felipe@gmail.com>", externo.toString());
    }

    @Test
    void testEsInvitable() {
        assertTrue(externo instanceof Invitable);
    }

    // Casos Extremos

    @Test
    void testSetNombreActualiza() {
        externo.setNombreCompleto("Javiera Aravena");
        assertEquals("Javiera Aravena", externo.getNombreCompleto());
    }

    @Test
    void testSetCorreoActualiza() {
        externo.setCorreo("javiera@gmail.com");
        assertEquals("javiera@gmail.com", externo.getCorreo());
    }
}