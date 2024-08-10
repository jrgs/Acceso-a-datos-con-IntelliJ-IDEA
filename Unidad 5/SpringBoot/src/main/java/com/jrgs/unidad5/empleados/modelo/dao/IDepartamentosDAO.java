package com.jrgs.unidad5.empleados.modelo.dao;

import com.jrgs.unidad5.empleados.modelo.entidades.EntidadDepartamentos;
import com.jrgs.unidad5.empleados.modelo.entidades.EntidadEmpleados;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDepartamentosDAO extends CrudRepository<EntidadDepartamentos, Integer> {
    List<EntidadDepartamentos> findByDepnoGreaterThan(int depno);

    List<EntidadDepartamentos> findByUbicacionIsIgnoreCase(String ubicacion);

    List<EntidadDepartamentos> findByUbicacionEqualsOrUbicacionEquals(String firstPlace, String secondPlace);
}

