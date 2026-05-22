package com.reuniones;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class EmpleadoTest {
    Empleado emp;
    Departamento dep;

    @BeforeEach
    void setUp() throws EmpleadoSinNombreException, EmpleadoSinCorreoException, DepartamentoSinNombreException{
        emp = new Empleado("E001", "Martin", "García", "margarcia@udec.cl");
        dep =new Departamento("Desarrollo");
    }
    // Casos Normales
    @Test
    void testNombreSeGuardaCorrectamente(){
        assertEquals("Martin", emp.getNombre());
    }
    @Test
    void testApellidosSeGuardaCorrectamente(){
        assertEquals("García", emp.getApellidos());
    }
    @Test
    void testCorreoSeGuardaCorrectamente(){
        assertEquals("margarcia@udec.cl", emp.getCorreo());
    }
    @Test
    void testIdSeGuardaCorrectamente(){
        assertEquals("E001",emp.getId());
    }
    @Test
    void testAsignarDepartamento(){
        emp.setDepartamento(dep);
        assertEquals(dep,emp.getDepartamento());
    }
    @Test
    void testAsignarDepartamentoAgregaEmpleadoAlDepto() {
        emp.setDepartamento(dep);
        assertEquals(1 ,dep.obtenerCantidadEmpleados());
    }
    @Test
    void testToString(){
        assertEquals("[E001] Martin García <margarcia@udec.cl>",emp.toString());
    }
    // Casos Extremos
    @Test
    void testNombreNullLanzaExcepcion(){
        assertThrows(EmpleadoSinNombreException.class, ()-> new Empleado("E001",null,"García","margarcía@udec.cl")
        );
    }
    @Test
    void testNombreVacíoLanzaExepcion(){
        assertThrows(EmpleadoSinNombreException.class, ()-> new Empleado("E001","","García","margarcia@udec.cl"));
    }
    @Test
    void testCorreoNullLanzaExcepcion(){
        assertThrows(EmpleadoSinCorreoException.class, ()-> new Empleado("E001", "Martin", "García", null));
    }
    @Test
    void testCorreoVacíoLanzaExepcion(){
        assertThrows(EmpleadoSinCorreoException.class, ()-> new Empleado("E001", "Martin", "García", ""));
    }
}




