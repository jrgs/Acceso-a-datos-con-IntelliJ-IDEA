package com.jrgs.unidad5.empleados.servicios;

import com.jrgs.unidad5.empleados.modelo.dao.IEmpleadosDAO;
import com.jrgs.unidad5.empleados.modelo.entidades.EntidadEmpleados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioEmpleados {
    @Autowired
    private IEmpleadosDAO empleadosDAO;

    public List<EntidadEmpleados> buscarEmpleados() {
        return (List<EntidadEmpleados>) empleadosDAO.findAll();
    }

    public Optional<EntidadEmpleados> buscarEmpleadoPorCodigo(int id) {
        return empleadosDAO.findById(id);
    }

    public EntidadEmpleados guardarEmpleado(EntidadEmpleados empleado) {
        return empleadosDAO.save(empleado);
    }

    public boolean actualizarEmpleado(int id, EntidadEmpleados nuevoEmpleado) {
        Optional<EntidadEmpleados> empleado = buscarEmpleadoPorCodigo(id);
        if(empleado.isPresent()) {
            empleado.get().setNombre(nuevoEmpleado.getNombre());
            empleado.get().setPuesto(nuevoEmpleado.getPuesto());
            empleado.get().setDepno(nuevoEmpleado.getDepno());
            empleadosDAO.save(empleado.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean borrarEmpleado(int id) {
        Optional<EntidadEmpleados> empleado = buscarEmpleadoPorCodigo(id);
        if(empleado.isPresent()) {
            empleadosDAO.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<EntidadEmpleados> buscarEmpleadosPorPuesto(String puesto) {
        return empleadosDAO.findByPuestoContains(puesto);
    }
}
