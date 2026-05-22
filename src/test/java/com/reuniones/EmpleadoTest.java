package com.reuniones;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class EmpleadoTest {
    @Test
    void testCrearEmpleado() {
        Empleado emp = new Empleado("E001", "Felipe", "de la Fuente", "fdelafuente@udec.cl");
        assertEquals("Felipe", emp.getNombre());
        assertEquals("de la Fuente", emp.getApellidos());
        assertEquals("fdelafuente@udec.cl", emp.getCorreo());
    }

    @Test
    void testAsignarDepartamento(){
        Empleado emp = new Empleado("E001", "Felipe", "de la Fuente", "fdelafuente@udec.cl");
        Departamento dep = new Departamento("Desarrollo");
        emp.setDepartamento(dep);
        assertEquals(dep, emp.getDepartamento());
    }

    @Test
    void testAsignarDepartamentoAgregaEmpleadoAlDepartamento(){
        Empleado emp = new Empleado("E001", "Felipe", "de la Fuente", "fdelafuente@udecl.cl");
        Departamento dep = new Departamento("Desarrollo");
        emp.setDepartamento(dep);
        assertEquals(1, dep.obtenerCantidadEmpleados());

    }
}




