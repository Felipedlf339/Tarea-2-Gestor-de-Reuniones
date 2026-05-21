package com.reuniones;
import java.util.ArrayList;
import java.util.List;

public class Departamento implements Invitable {
    private String nombre;
    private List<Empleado> empleados;

    /**
     * Constructor del departamento.
     * @param nombre nombre del departamento
     */
    public Departamento(String nombre) {
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }

    /**
     * Devuelve la cantidad de empleados del departamento.
     * @return  numero de empleados
     */
    public int obtenerCantidadEmpleados() {
        return empleados.size();
    }

    /**
     * Agrega un empleado al departamento
     * @param empleado empleado a agregar
     */
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    @Override
    public void invitar(){
        System.out.println("Invitando al departamento: " + nombre);
        for(Empleado empleado : empleados){
            empleado.invitar();
        }
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public List<Empleado> getEmpleados() {return empleados;}


    @Override
    public String toString() {
        return "Departamento: " + nombre + " (" + obtenerCantidadEmpleados() + " empleados)";
    }

}
