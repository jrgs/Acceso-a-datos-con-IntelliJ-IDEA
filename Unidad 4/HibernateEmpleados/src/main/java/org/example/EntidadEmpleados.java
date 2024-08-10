package org.example;

import javax.persistence.*;

@Entity
@Table(name = "empleados", schema = "public", catalog = "Empleados")
public class EntidadEmpleados {
    private int empno;
    private String nombre;
    private String puesto;
    private EntidadDepartamentos departamento;

    @Id
    @Column(name = "empno", nullable = false)
    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    @Basic
    @Column(name = "nombre", nullable = true, length = 10)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "puesto", nullable = true, length = 15)
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntidadEmpleados that = (EntidadEmpleados) o;

        if (empno != that.empno) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (puesto != null ? !puesto.equals(that.puesto) : that.puesto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = empno;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (puesto != null ? puesto.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "depno", referencedColumnName = "depno")
    public EntidadDepartamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(EntidadDepartamentos departamento) {
        this.departamento = departamento;
    }
}
