package com.reuniones;
import java.util.ArrayList;
import java.util.List;

public class Departamento implements Invitable {
    private String nombre;
    private List<Empleado> empleados;
    private String correo;

    /**
     * Constructor del Departamento
     * @param nombre nombre del departamento
     * @param correo dirección de correo del departamento
     * @throws EmpleadoSinCorreoException si el nombre del departamento es null o vacío
     */
    public Departamento(String nombre, String correo) throws DepartamentoSinNombreException {
        if(nombre == null || nombre.isEmpty()){
            throw new DepartamentoSinNombreException("El departamento debe tener un nombre válido");
        }
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
        this.correo = correo;

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

    /**
     * @return correo del departamento
     */
    @Override
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {this.correo = correo;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public List<Empleado> getEmpleados() {return empleados;}


    @Override
    public String toString() {
        return "Departamento: " + nombre + " (" + obtenerCantidadEmpleados() + " empleados)";
    }

}
