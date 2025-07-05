package org.example;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "prestamos")
@Where(clause = "fechaprestamo is null")
public class Prestamos {
    private int id;
    private Date fechaprestamo;
    private Date fechadevolucion;
    private Libros libro;
    private Usuarios usuario;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fechaprestamo", nullable = false)
    public Date getFechaprestamo() {
        return fechaprestamo;
    }

    public void setFechaprestamo(Date fechaprestamo) {
        this.fechaprestamo = fechaprestamo;
    }

    @Basic
    @Column(name = "fechadevolucion", nullable = true)
    public Date getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(Date fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prestamos that = (Prestamos) o;

        if (id != that.id) return false;
        if (fechaprestamo != null ? !fechaprestamo.equals(that.fechaprestamo) : that.fechaprestamo != null)
            return false;
        if (fechadevolucion != null ? !fechadevolucion.equals(that.fechadevolucion) : that.fechadevolucion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fechaprestamo != null ? fechaprestamo.hashCode() : 0);
        result = 31 * result + (fechadevolucion != null ? fechadevolucion.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "libro", referencedColumnName = "isbn", nullable = false)
    public Libros getLibro() {
        return libro;
    }

    public void setLibro(Libros libro) {
        this.libro = libro;
    }

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "codigo", nullable = false)
    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
