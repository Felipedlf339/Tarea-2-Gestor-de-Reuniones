package com.reuniones;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class InformeTest {

    ReunionVirtual reunion;
    Empleado organizador;
    String rutaArchivo = "informe_test.txt";

    @BeforeEach
    void setUp() throws EmpleadoSinNombreException, EmpleadoSinCorreoException, EnlaceInvalidoException {
        organizador = new Empleado("E001", "Felipa", "Garcia", "jaraveñav@udec.cl");

        reunion = new ReunionVirtual(
                LocalDate.now(),                         // 1. fecha
                LocalTime.of(15, 30),       // 2. horaPrevista
                Duration.ofMinutes(45),                  // 3. duracionPrevista
                TipoReunion.TECNICA,                     // 4. tipo
                organizador,                             // 5. organizador
                "https://zoom.us/j/12345"                // 6. enlace
        );
    }

    // Casos Normales
    @Test
    void testGenerarInformeCreaArchivoFisico() {
        Path ruta = Paths.get(rutaArchivo);

        GeneradorInforme.generar(reunion, rutaArchivo);

        assertTrue(Files.exists(ruta), "El archivo .txt debería haberse creado en el disco.");
    }

    // Casos Extremos
    @Test
    void testGenerarInformeNoLanzaExcepcionConDatosIncompletos() {
        assertDoesNotThrow(() -> GeneradorInforme.generar(reunion, rutaArchivo));
    }

    // Limpieza después de cada Test para no dejar basura
    @AfterEach
    void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get(rutaArchivo));
    }
}