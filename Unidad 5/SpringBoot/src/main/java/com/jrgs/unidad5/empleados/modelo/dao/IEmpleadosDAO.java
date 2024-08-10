package com.jrgs.unidad5.empleados.modelo.dao;

import com.jrgs.unidad5.empleados.modelo.entidades.EntidadEmpleados;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmpleadosDAO extends CrudRepository<EntidadEmpleados, Integer> {
    List<EntidadEmpleados> findByNombreStartsWith(String prefix);

    List<EntidadEmpleados> findByPuestoContains(String string);
}
