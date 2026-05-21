package com.reuniones;

/**
 * Representa un empleado de la empresa.
 * Puede ser invitado individualmente a una reunión.
 */

public class Empleado implements Invitable {
    private String id;
    private String nombre;
    private String apellidos;
    private String correo;
    private Departamento departamento;

    /**
     * Constructor del empleado.
     * @param id
     * @param nombre
     * @param apellidos
     * @param correo
     */

    public Empleado(String id, String nombre, String apellidos, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    /**
     * Imprime una invitacion en la consola.
     */
    @Override
    public void invitar(){
        System.out.println("Invitando a: " + nombre + " "+ apellidos + " <" + correo + ">");
    }

    public String getId() {return id;}
    public String getNombre() {return nombre;}
    public String getApellidos() {return apellidos;}
    public String getCorreo() {return correo;}
    public Departamento getDepartamento() {return departamento;}

    public void setId(String id) {this.id = id;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setApellidos(String apellidos) {this.apellidos = apellidos;}
    public void setCorreo(String correo) {this.correo = correo;}

    /**
     * Asigna el empleado a un departamento y lo agrega a la lista de dicho departamento.
     * @param departamento departamento al que pertenece.
     */
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
        departamento.agregarEmpleado(this);
    }


    @Override
    public String toString() {
        return "[" + id + "]" + nombre + " " + apellidos + " <" + correo + ">";
    }

}
