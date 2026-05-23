package com.reuniones;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DepartamentoTest {
    Departamento dep;

    @BeforeEach
    void setUp() throws DepartamentoSinNombreException {
        dep = new Departamento("Marketing");
    }

    // Casos Normales

    @Test
    void testNombreSeGuardaCorrectamente() {
        assertEquals("Marketing", dep.getNombre());
    }

    @Test
    void testDepartamentoVacioTieneCeroEmpleados() {
        assertEquals(0, dep.obtenerCantidadEmpleados());
    }

    @Test
    void testAgregarEmpleadoIncrementaCantidad() throws EmpleadoSinNombreException, EmpleadoSinCorreoException {
        Empleado emp = new Empleado("E001", "Ana", "García", "ana@empresa.com");
        dep.agregarEmpleado(emp);
        assertEquals(1, dep.obtenerCantidadEmpleados());
    }

    @Test
    void testAgregarVariosEmpleados() throws EmpleadoSinNombreException, EmpleadoSinCorreoException {
        dep.agregarEmpleado(new Empleado("E001", "Ana", "García", "ana@empresa.com"));
        dep.agregarEmpleado(new Empleado("E002", "Carlos", "López", "carlos@empresa.com"));
        assertEquals(2, dep.obtenerCantidadEmpleados());
    }

    @Test
    void testToString() {
        assertEquals("Departamento: Marketing (0 empleados)", dep.toString());
    }

    // Casos Extremos

    @Test
    void testNombreNullLanzaExcepcion() {
        assertThrows(DepartamentoSinNombreException.class, () -> new Departamento(null));
    }

    @Test
    void testNombreVacioLanzaExcepcion() {
        assertThrows(DepartamentoSinNombreException.class, () -> new Departamento(""));
    }
}
