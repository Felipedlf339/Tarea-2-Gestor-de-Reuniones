package com.reuniones;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvitableTest {

    //Empleado es invitable

    @Test
    void testEmpleadoEsInvitable() throws EmpleadoSinNombreException, EmpleadoSinCorreoException {
        Empleado emp = new Empleado("E001", "Felipe", "de la Fuente", "felipe@gmail.com");
        assertTrue(emp instanceof Invitable);
    }

    //Departamento es invitable

    @Test
    void testDepartamentoEsInvitable() throws DepartamentoSinNombreException {
        Departamento dep = new Departamento("Marketing");
        assertTrue(dep instanceof Invitable);
    }

    //Invitado externo es invitable

    @Test
    void testInvitadoExternoEsInvitable() {
        InvitadoExterno ext = new InvitadoExterno("Juan Perez", "juan@gmail.com");
        assertTrue(ext instanceof Invitable);
    }

    //Todos pueden invitar

    @Test
    void testEmpleadoPuedeInvitar() throws EmpleadoSinNombreException, EmpleadoSinCorreoException {
        Invitable emp = new Empleado("E001", "Felipe", "de la Fuente", "felipe@empresa.com");
        assertDoesNotThrow(() -> emp.invitar());
    }

    @Test
    void testDepartamentoPuedeInvitar() throws DepartamentoSinNombreException {
        Invitable dep = new Departamento("Marketing");
        assertDoesNotThrow(() -> dep.invitar());
    }

    @Test
    void testInvitadoExternoPuedeInvitar() {
        Invitable ext = new InvitadoExterno("Juan Perez", "juan@gmail.com");
        assertDoesNotThrow(() -> ext.invitar());
    }
}