package org.example;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link EntidadEmpleados} entity
 */
public class EntidadEmpleadosDto implements Serializable {
    private int empno;
    private String nombre;
    private String puesto;
    private int departamentoDepno;
    private String departamentoNombre;

    public EntidadEmpleadosDto() {
    }

    public EntidadEmpleadosDto(int empno, String nombre, String puesto, int departamentoDepno, String departamentoNombre) {
        this.empno = empno;
        this.nombre = nombre;
        this.puesto = puesto;
        this.departamentoDepno = departamentoDepno;
        this.departamentoNombre = departamentoNombre;
    }

    public int getEmpno() {
        return empno;
    }

    public EntidadEmpleadosDto setEmpno(int empno) {
        this.empno = empno;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public EntidadEmpleadosDto setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getPuesto() {
        return puesto;
    }

    public EntidadEmpleadosDto setPuesto(String puesto) {
        this.puesto = puesto;
        return this;
    }

    public int getDepartamentoDepno() {
        return departamentoDepno;
    }

    public EntidadEmpleadosDto setDepartamentoDepno(int departamentoDepno) {
        this.departamentoDepno = departamentoDepno;
        return this;
    }

    public String getDepartamentoNombre() {
        return departamentoNombre;
    }

    public EntidadEmpleadosDto setDepartamentoNombre(String departamentoNombre) {
        this.departamentoNombre = departamentoNombre;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadEmpleadosDto entity = (EntidadEmpleadosDto) o;
        return Objects.equals(this.empno, entity.empno) &&
                Objects.equals(this.nombre, entity.nombre) &&
                Objects.equals(this.puesto, entity.puesto) &&
                Objects.equals(this.departamentoDepno, entity.departamentoDepno) &&
                Objects.equals(this.departamentoNombre, entity.departamentoNombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empno, nombre, puesto, departamentoDepno, departamentoNombre);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "empno = " + empno + ", " +
                "nombre = " + nombre + ", " +
                "puesto = " + puesto + ", " +
                "departamentoDepno = " + departamentoDepno + ", " +
                "departamentoNombre = " + departamentoNombre + ")";
    }
}