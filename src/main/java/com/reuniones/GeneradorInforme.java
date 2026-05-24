package com.reuniones;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Clase encargada de generar un archivo de texto con los datos de la reunión
 * Deja escrita la informacion
 */
public class GeneradorInforme {

    /**
     * Genera y guarda un archivo de texto con el resumen detallado de la reunión.
     * Este método utiliza java.nio.file para garantizar una escritura segura y
     * un cierre automático del archivo (Try-with-resources)
     * @param reunion La reunión de donde se sacarán los datos y que puede ser virtual o presencial
     * @param rutaArchivo El nombre o ruta donde se guardará el archivo de texto
     */
    public static void generar(Reunion reunion, String rutaArchivo) {

        // Aqui se configura la ruta y nombre del archivo
        Path ruta = Paths.get(rutaArchivo);

        // Usamos try por si llegase a haber algun error
        try (BufferedWriter writer = Files.newBufferedWriter(ruta)) {

            writer.write("     ***** INFORME DE REUNIÓN *****     \n\n\n");

            writer.write("     *DATOS DE TIEMPO*    \n");
            writer.write("Fecha: " + reunion.getFecha() + "\n");
            writer.write("Hora Prevista: " + reunion.getHoraPrevista() + "\n");
            writer.write("Duración Prevista: " + reunion.getDuracionPrevista().toMinutes() + " minutos\n");
            writer.write("Hora de Inicio Real: " + reunion.getHoraInicio() + "\n");
            writer.write("Hora de Fin Real: " + reunion.getHoraFin() + "\n");
            try {
                writer.write("Duración Total: " + reunion.calcularTiempoReal() + " minutos\n\n");
            } catch (ReunionException e) {
                writer.write("Duración Total: Pendiente (" + e.getMessage() + ")\n\n\n");
            }


            writer.write("     *UBICACIÓN Y TIPO*    \n");
            writer.write("Tipo: " + reunion.getTipo() + "\n");

            // Aqui se revisa si la reunión fue presencial o virtual, para anotar los datos correspondientes
            if (reunion instanceof ReunionVirtual) {
                ReunionVirtual rv = (ReunionVirtual) reunion;
                writer.write("Modalidad: Virtual\n");
                writer.write("Enlace de la llamada: " + rv.getEnlace() + "\n\n");
            } else if (reunion instanceof ReunionPresencial) {
                ReunionPresencial rp = (ReunionPresencial) reunion;
                writer.write("Modalidad: Presencial\n");
                writer.write("Sala asignada: " + rp.getSala() + "\n\n\n");
            }

            // Aqui usamos un for para anotar a todos los que asistieron
            writer.write("     *ASISTENCIA Y RETRASOS*    \n");
            writer.write("Lista Asistentes:\n");
            List<Asistencia> asistencias = reunion.obtenerAsistencias();
            for (Asistencia a : asistencias) {
                writer.write("~ " + a.toString() + " ~\n");
            }
            writer.write("Total de asistentes: " + reunion.obtenerTotalAsistencia() + "\n");
            writer.write("Porcentaje de asistencia: " + String.format("%.2f", reunion.obtenerPorcentajeAsistencia()) + "%\n");

            List<Retraso> retrasos = reunion.obtenerRetrasos();
            if (!retrasos.isEmpty()) {
                writer.write("\nDetalle de Retrasos:\n");
                for (Retraso r : retrasos) {
                    writer.write("~ " + r.toString() + " ~\n");
                }
            }
            writer.write("\n\n");


            writer.write("     *NOTAS DE LA REUNIÓN*    \n");
            List<Nota> notas = reunion.getNotas();
            if (notas != null && !notas.isEmpty()) {
                for (int i = 0; i < notas.size(); i++) {
                    writer.write((i + 1) + ". " + notas.get(i).getContenido() + "\n");
                }
            } else {
                writer.write("No se registraron notas durante el transcurso de esta reunión.\n");
            }


        } catch (IOException e) {
            System.err.println("Ocurrió un error al escribir el archivo txt: " + e.getMessage());
        }
    }
}