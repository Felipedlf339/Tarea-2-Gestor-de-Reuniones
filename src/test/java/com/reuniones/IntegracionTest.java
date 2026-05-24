package com.reuniones;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class IntegracionTest {

    @Test
    void testFlujoCompletoDeReunionYGeneracionDeInforme() throws Exception {

        // Se crean los participantes de la reunión
        Empleado organizador = new Empleado("E001", "Martin", "Garcia", "marga@empresa.cl");
        Empleado empleadoAtrasado = new Empleado("E002", "Felipe", "De la Fuente", "felipe@empresa.com");
        InvitadoExterno externoPuntual = new InvitadoExterno("Juan Perez", "juan@gmail.com");
        Empleado empleadoAusente = new Empleado("E003", "Javiera", "Aravena", "jarav@empresa.com");

        // Datos de la reunión
        ReunionPresencial reunion = new ReunionPresencial(
                LocalDate.now(),
                LocalTime.of(10, 0),
                Duration.ofMinutes(60),
                TipoReunion.TECNICA,
                organizador,
                "Sala de Conferencias B"
        );

        // Se generan las invitaciones
        reunion.getInvitaciones().add(new Invitacion(empleadoAtrasado));
        reunion.getInvitaciones().add(new Invitacion(externoPuntual));
        reunion.getInvitaciones().add(new Invitacion(empleadoAusente));


        // Inicia la reunión
        reunion.iniciar();
        Instant momentoInicio = reunion.getHoraInicio();

        // Llega Juan
        Instant llegadaJuan = momentoInicio.plus(Duration.ofSeconds(1));
        reunion.agregarRetraso(new Retraso(externoPuntual, llegadaJuan));

        // Llega Felipe
        Instant llegadaFelipe = momentoInicio.plus(Duration.ofMinutes(15));
        reunion.agregarRetraso(new Retraso(empleadoAtrasado, llegadaFelipe));


        // Se toman las notas
        reunion.nuevaNota(new Nota("Felipe se queja de que Javiera nunca llega a las reuniones"));
        reunion.nuevaNota(new Nota("Felipe presenta sus ideas sobre las mejoras en infraestructura"));
        reunion.nuevaNota(new Nota("Martin propone hacer una votacion para decidir si Javiera deberia ser expulsada"));
        reunion.nuevaNota(new Nota("Resultado de la votación: con 2 votos a su favor, Javiera no será expulsada"));

        // Termina la reunión
        reunion.finalizar();

        // Se genera el informe
        String rutaArchivo = "informe_integracion_presencial.txt";
        assertDoesNotThrow(() -> GeneradorInforme.generar(reunion, rutaArchivo));

        // Se verifica la creación correcta del informe
        Path ruta = Paths.get(rutaArchivo);
        assertTrue(Files.exists(ruta), "El informe final presencial debe existir.");
    }
}