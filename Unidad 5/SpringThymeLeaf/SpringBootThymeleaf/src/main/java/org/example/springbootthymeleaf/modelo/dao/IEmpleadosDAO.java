package org.example.springbootthymeleaf.modelo.dao;

import org.example.springbootthymeleaf.modelo.entidades.EntidadEmpleados;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmpleadosDAO extends CrudRepository<EntidadEmpleados, Integer> {
    List<EntidadEmpleados> findByNombreStartsWith(String prefix);
    List<EntidadEmpleados> findByPuestoContains(String string);
    List<EntidadEmpleados> findByDepno(int depno);
}
