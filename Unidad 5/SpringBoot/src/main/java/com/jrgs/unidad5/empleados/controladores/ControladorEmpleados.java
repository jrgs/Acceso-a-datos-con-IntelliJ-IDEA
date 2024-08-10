package com.jrgs.unidad5.empleados.controladores;

import com.jrgs.unidad5.empleados.modelo.dao.IDepartamentosDAO;
import com.jrgs.unidad5.empleados.modelo.dao.IEmpleadosDAO;
import com.jrgs.unidad5.empleados.modelo.dto.EmpleadosDTO;
import com.jrgs.unidad5.empleados.modelo.entidades.*;
import com.jrgs.unidad5.empleados.servicios.ServicioEmpleados;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
public class ControladorEmpleados {
    @Autowired
    ServicioEmpleados servicioEmpleados;

    @GetMapping
    public List<EntidadEmpleados> buscarEmpleados(@RequestParam(name = "puesto", required = false) String puesto) {
        if (puesto == null)
            return servicioEmpleados.buscarEmpleados();
        else
            return servicioEmpleados.buscarEmpleadosPorPuesto(puesto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadEmpleados> buscarEmpleadoPorId(@PathVariable(value = "id") int id) {
        Optional<EntidadEmpleados> empleado = servicioEmpleados.buscarEmpleadoPorCodigo(id);
        if(empleado.isPresent()) {
            return ResponseEntity.ok().body(empleado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarEmpleado(@Validated @RequestBody EntidadEmpleados empleado) {
        if (!servicioEmpleados.buscarEmpleadoPorCodigo(empleado.getEmpno()).isPresent())
            return ResponseEntity.ok().body(servicioEmpleados.guardarEmpleado(empleado));
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@RequestBody EntidadEmpleados nuevoEmpleado,
                                                @PathVariable(value = "id") int id) {
        if(servicioEmpleados.actualizarEmpleado(id, nuevoEmpleado)) {
            return ResponseEntity.ok().body("Updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEmpleado(@PathVariable(value = "id") int id) {
        if(servicioEmpleados.borrarEmpleado(id)) {
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
/*
    @Autowired
    IEmpleadosDAO empleadosDAO;

    @GetMapping
    public List<EntidadEmpleados> buscarEmpleados(@RequestParam(name = "puesto", required = false) String puesto) {
        if (puesto == null)
            return (List<EntidadEmpleados>) empleadosDAO.findAll();
        else
            return (List<EntidadEmpleados>) empleadosDAO.findByPuestoContains(puesto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadEmpleados> buscarEmpleadoPorId(@PathVariable(value = "id") int id) {
        Optional<EntidadEmpleados> empleado = empleadosDAO.findById(id);
        if (empleado.isPresent())
            return ResponseEntity.ok().body(empleado.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> guardarEmpleado(@Validated @RequestBody EntidadEmpleados empleado) {
        if (!empleadosDAO.existsById(empleado.getEmpno()))
            return ResponseEntity.ok().body(empleadosDAO.save(empleado));
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@RequestBody EntidadEmpleados nuevoEmpleado,
                                                @PathVariable(value = "id") int id) {
        if(empleadosDAO.existsById(id)) {
            empleadosDAO.save(nuevoEmpleado);
            return ResponseEntity.ok().body("Updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEmpleado(@PathVariable(value = "id") int id) {
        if(empleadosDAO.existsById(id)) {
            empleadosDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    IDepartamentosDAO departamentosDAO;

    @GetMapping("dto/{id}")
    public ResponseEntity<EmpleadosDTO> buscarEmpleadoDTOporIdv1(@PathVariable(value = "id") int id) {
        Optional<EntidadEmpleados> empleado = empleadosDAO.findById(id);

        if (empleado.isPresent()) {
            Optional<EntidadDepartamentos> departamento = departamentosDAO.findById(empleado.get().getDepno());

            EmpleadosDTO empleadoDTO = new EmpleadosDTO();
            empleadoDTO.setEmpno(empleado.get().getEmpno());
            empleadoDTO.setNombre(empleado.get().getNombre());
            empleadoDTO.setPuesto(empleado.get().getPuesto());
            empleadoDTO.setDepno(empleado.get().getDepno());
            empleadoDTO.setDepartamentoNombre(departamento.get().getNombre());
            empleadoDTO.setDepartamentoUbicacion(departamento.get().getUbicacion());

            return ResponseEntity.ok().body(empleadoDTO);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("dto/{id}")
    public ResponseEntity<EmpleadosDTO> buscarEmpleadoDTOporId(@PathVariable(value = "id") int id) {
        Optional<EntidadEmpleados> empleado = empleadosDAO.findById(id);

        if (empleado.isPresent()) {
            Optional<EntidadDepartamentos> departamento = departamentosDAO.findById(empleado.get().getDepno());

            ModelMapper mapper = new ModelMapper();
            EmpleadosDTO empleadoDTO = mapper.map(empleado.get(), EmpleadosDTO.class);
            mapper.typeMap(EntidadDepartamentos.class, EmpleadosDTO.class).
                    addMappings( mapping -> mapping.skip(EmpleadosDTO::setNombre) );
            mapper.map(departamento.get(), empleadoDTO);

            return ResponseEntity.ok().body(empleadoDTO);
        }
        else
            return ResponseEntity.notFound().build();
    }
    */
}