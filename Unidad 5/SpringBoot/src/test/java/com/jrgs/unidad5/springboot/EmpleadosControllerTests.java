package com.jrgs.unidad5.springboot;

import com.jrgs.unidad5.empleados.Application;
import com.jrgs.unidad5.empleados.modelo.entidades.EntidadEmpleados;
import com.jrgs.unidad5.empleados.controladores.ControladorEmpleados;
import com.jrgs.unidad5.empleados.servicios.ServicioEmpleados;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
class EmpleadosControllerTests {

    @Mock
    private ServicioEmpleados servicioEmpleados;

    @InjectMocks
    private ControladorEmpleados controladorEmpleados;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks antes de cada prueba
    }

    @Test
    void buscarEmpleados() {
        // Arrange
        List<EntidadEmpleados> empleados = new ArrayList<>();
        empleados.add(new EntidadEmpleados(1000, "Nombre1", "Puesto1", 10));
        empleados.add(new EntidadEmpleados(1001, "Nombre2", "Puesto2", 20));

        when(servicioEmpleados.buscarEmpleados()).thenReturn(empleados);

        // Act
        List<EntidadEmpleados> result = controladorEmpleados.buscarEmpleados(null);

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void buscarEmpleadoPorIdExistente() {
        // Arrange
        int id = 1000;
        EntidadEmpleados empleado = new EntidadEmpleados(id, "Nombre1", "Puesto1", 10);

        when(servicioEmpleados.buscarEmpleadoPorCodigo(id)).thenReturn(Optional.of(empleado));

        // Act
        ResponseEntity<EntidadEmpleados> result = controladorEmpleados.buscarEmpleadoPorId(id);

        // Assert
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(empleado, result.getBody());
    }

    @Test
    void buscarEmpleadoPorIdNoExistente() {
        // Arrange
        int id = 1099;

        when(servicioEmpleados.buscarEmpleadoPorCodigo(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<EntidadEmpleados> result = controladorEmpleados.buscarEmpleadoPorId(id);

        // Assert
        assertEquals(404, result.getStatusCodeValue());
    }

    @Test
    void guardarEmpleado() {
        // Arrange
        EntidadEmpleados empleado = new EntidadEmpleados(1000, "Nombre1", "Puesto1", 10);

        when(servicioEmpleados.guardarEmpleado(any(EntidadEmpleados.class))).thenReturn(empleado);

        // Act
        ResponseEntity<?> result = controladorEmpleados.guardarEmpleado(empleado);

        // Assert
        assertEquals(empleado, (EntidadEmpleados) result.getBody());
    }

    @Test
    void actualizarEmpleadoExistente() {
        // Arrange
        int id = 1000;
        EntidadEmpleados nuevoEmpleado = new EntidadEmpleados(id, "NuevoNombre", "NuevoPuesto", 10);
        when(servicioEmpleados.actualizarEmpleado(id, nuevoEmpleado)).thenReturn(true);

        // Act
        ResponseEntity<?> result = controladorEmpleados.actualizarEmpleado(nuevoEmpleado, id);

        // Assert
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Updated", result.getBody());
   }

    @Test
    void actualizarEmpleadoNoExistente() {
        // Arrange
        int id = 1099;
        EntidadEmpleados nuevoEmpleado = new EntidadEmpleados(1000, "NuevoNombre", "NuevoPuesto", 10);

        when(servicioEmpleados.actualizarEmpleado(id, nuevoEmpleado)).thenReturn(false);

        // Act
        ResponseEntity<?> result = controladorEmpleados.actualizarEmpleado(nuevoEmpleado, id);

        // Assert
        assertEquals(404, result.getStatusCodeValue());
    }

    @Test
    void borrarEmpleadoExistente() {
        // Arrange
        int id = 1000;

        when(servicioEmpleados.borrarEmpleado(id)).thenReturn(true);

        // Act
        ResponseEntity<?> result = controladorEmpleados.borrarEmpleado(id);

        // Assert
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Borrado", result.getBody());
        verify(servicioEmpleados, times(1)).borrarEmpleado(id); // Verificar que se llamó al método deleteById
    }

    @Test
    void borrarEmpleadoNoExistente() {
        // Arrange
        int id = 1099;

       when(servicioEmpleados.borrarEmpleado(id)).thenReturn(false);

        // Act
        ResponseEntity<?> result = controladorEmpleados.borrarEmpleado(id);

        // Assert
        assertEquals(404, result.getStatusCodeValue());
    }
}
