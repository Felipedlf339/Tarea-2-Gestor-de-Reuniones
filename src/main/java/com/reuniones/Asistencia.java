package com.reuniones;

/**
 * Representa el registro de asistencia de un empleado a una reunión
 * Esta clase sirve para confirmar que un participante estuvo presente
 */
public class Asistencia {

    private Empleado empleado;

    /**
     * Constructor de la clase Asistencia
     * Crea un nuevo registro de presencia para un empleado específico
     * @param empleado El empleado que está registrando su asistencia a la reunión
     */
    public Asistencia(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * Obtiene el empleado asociado a este registro de asistencia
     * @return El objeto Empleado que asistió a la reunión
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * Modifica o asigna el empleado asociado a este registro de asistencia
     * @param empleado El nuevo objeto Empleado que registrará su asistencia
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * Genera una representación en texto del registro de asistencia
     * @return Una cadena de texto indicando la confirmación de asistencia y los datos del empleado
     */
    @Override
    public String toString() {
        if (empleado != null) {
            return "Asistencia confirmada de: " + empleado.getNombre() + " " + empleado.getApellidos();
        }
        return "Asistencia de empleado desconocido";
    }
}