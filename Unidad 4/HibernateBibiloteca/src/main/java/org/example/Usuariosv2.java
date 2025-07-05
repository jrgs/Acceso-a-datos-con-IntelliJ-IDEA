package org.example;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuariosv2 {
    private String codigo;
    private String nombre;
    private String apellidos;
    private Date fechanacimiento;
    private List<Prestamosv2> librosEnPrestamo;

    @Id
    @Column(name = "codigo", nullable = false, length = 8)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "nombre", nullable = false, length = 25)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "apellidos", nullable = false, length = 25)
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Basic
    @Column(name = "fechanacimiento", nullable = true)
    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuariosv2 that = (Usuariosv2) o;

        if (codigo != null ? !codigo.equals(that.codigo) : that.codigo != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (apellidos != null ? !apellidos.equals(that.apellidos) : that.apellidos != null) return false;
        if (fechanacimiento != null ? !fechanacimiento.equals(that.fechanacimiento) : that.fechanacimiento != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo != null ? codigo.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (fechanacimiento != null ? fechanacimiento.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "prestatario")
    @Where(clause = "fechadevolucion is null")
    public List<Prestamosv2> getLibrosEnPrestamo() {
        return librosEnPrestamo;
    }

    public void setLibrosEnPrestamo(List<Prestamosv2> librosEnPrestamo) {
        this.librosEnPrestamo = librosEnPrestamo;
    }
}
