package com.reuniones;

public class InvitadoExterno implements Invitable {
    private String nombreCompleto;
    private String correo;

    /**
     * Constructor del invitado externo.
     * @param nombreCompleto nombre completo del invitado
     * @param correo correo del invitado.
     */
    public InvitadoExterno(String nombreCompleto, String correo) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
    }


    /**
     * Imprime una invitacion por consola
     */
    @Override
    public void invitar() {
        System.out.println("Invitando a externo: " + nombreCompleto + "<" + correo + ">");
    }

    public String getNombreCompleto() {return nombreCompleto;}
    public String getCorreo() {return correo;}
    public void setNombreCompleto(String nombreCompleto) {this.nombreCompleto = nombreCompleto;}
    public void setCorreo(String correo) {this.correo = correo;}

    @Override
    public String toString(){
        return "Externo: " + nombreCompleto + "<" + correo + ">";
    }






}
