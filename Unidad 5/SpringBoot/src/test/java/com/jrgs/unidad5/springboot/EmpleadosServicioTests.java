package com.jrgs.unidad5.springboot;

import com.jrgs.unidad5.empleados.Application;
import com.jrgs.unidad5.empleados.modelo.dao.IEmpleadosDAO;
import com.jrgs.unidad5.empleados.modelo.entidades.EntidadEmpleados;
import com.jrgs.unidad5.empleados.servicios.ServicioEmpleados;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
class EmpleadosServicioTests {

    @Mock
    private IEmpleadosDAO empleadosDAO;

    @InjectMocks
    private ServicioEmpleados servicioEmpleados;

    @Test
    void buscarEmpleados() {
        // Preparación
        List<EntidadEmpleados> empleados = new ArrayList<>();
        empleados.add(new EntidadEmpleados(1000, "Nombre1", "Puesto1", 10));
        empleados.add(new EntidadEmpleados(1001, "Nombre2", "Puesto2", 20));

        when(empleadosDAO.findAll()).thenReturn(empleados);

        // Ejecución
        List<EntidadEmpleados> result = servicioEmpleados.buscarEmpleados();

        // Comprobación
        assertEquals(2, result.size());
    }

    @Test
    void buscarEmpleadoPorIdExistente() {
        // Preparación
        int id = 1000;
        EntidadEmpleados empleado = new EntidadEmpleados(id, "Nombre1", "Puesto1", 10);

        when(empleadosDAO.findById(id)).thenReturn(Optional.of(empleado));

        // Ejecución
        Optional<EntidadEmpleados> result = servicioEmpleados.buscarEmpleadoPorCodigo(id);

        // Comprobación
        assertEquals(empleado, result.get());
    }

    @Test
    void buscarEmpleadoPorIdNoExistente() {
        // Preparación
        int id = 1099;

        when(empleadosDAO.findById(id)).thenReturn(Optional.empty());

        // Ejecución
        Optional<EntidadEmpleados> result = servicioEmpleados.buscarEmpleadoPorCodigo(id);

        // Comprobación
        assertEquals(result, Optional.empty());
    }

    @Test
    void guardarEmpleado() {
        // Preparación
        EntidadEmpleados empleado = new EntidadEmpleados(1000, "Nombre1", "Puesto1", 10);

        when(empleadosDAO.save(any(EntidadEmpleados.class))).thenReturn(empleado);

        // Ejecución
        EntidadEmpleados result = servicioEmpleados.guardarEmpleado(empleado);

        // Comprobación
        assertEquals(empleado, result);
    }

    @Test
    void actualizarEmpleadoExistente() {
        // Preparación
        int id = 1000;
        EntidadEmpleados nuevoEmpleado = new EntidadEmpleados(id, "NuevoNombre", "NuevoPuesto", 10);
        EntidadEmpleados empleadoExistente = new EntidadEmpleados(id, "NombreAntiguo", "PuestoAntiguo", 20);

        when(empleadosDAO.findById(id)).thenReturn(Optional.of(empleadoExistente));
        when(empleadosDAO.save(any(EntidadEmpleados.class))).thenReturn(empleadoExistente);

        // Ejecución
       boolean result = servicioEmpleados.actualizarEmpleado(id, nuevoEmpleado);

        // Comprobación
        assertEquals(result, true);
        assertEquals("NuevoNombre", empleadoExistente.getNombre()); // Verificar que el nombre se actualizó correctamente
    }

    @Test
    void actualizarEmpleadoNoExistente() {
        // Preparación
        int id = 1099;
        EntidadEmpleados nuevoEmpleado = new EntidadEmpleados(1000, "NuevoNombre", "NuevoPuesto", 10);

        when(empleadosDAO.findById(id)).thenReturn(Optional.empty());

        // Ejecución
        boolean result = servicioEmpleados.actualizarEmpleado(id, nuevoEmpleado);

        // Comprobación
        assertEquals(false, result);
    }

    @Test
    void borrarEmpleadoExistente() {
        // Preparación
        int id = 1000;
        EntidadEmpleados empleadoExistente = new EntidadEmpleados(id,"NombreAntiguo", "PuestoAntiguo", 10);

        when(empleadosDAO.findById(id)).thenReturn(Optional.of(empleadoExistente));

        // Ejecución
        boolean result = servicioEmpleados.borrarEmpleado(id);

        // Comprobación
        assertEquals(true, result);
        verify(empleadosDAO, times(1)).deleteById(id); // Verificar que se llamó al método deleteById
    }

    @Test
    void borrarEmpleadoNoExistente() {
        // Preparación
        int id = 1099;

        when(empleadosDAO.findById(id)).thenReturn(Optional.empty());

        // Ejecución
        boolean result = servicioEmpleados.borrarEmpleado(id);

        // Comprobación
        assertEquals(false, result);
    }
}

