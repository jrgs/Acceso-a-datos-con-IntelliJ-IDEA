package org.example;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "prestamos")
@IdClass(Prestamosv2PK.class)
public class Prestamosv2 {
    private Date fechaprestamo;
    private Date fechadevolucion;
    private String libro;
    private String usuario;
    private Librosv2 libroPrestado;
    private Usuariosv2 prestatario;

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

    @Id
    @Column(name = "libro", nullable = false, length = 13)
    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    @Id
    @Column(name = "usuario", nullable = false, length = 13)
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prestamosv2 that = (Prestamosv2) o;

        if (fechaprestamo != null ? !fechaprestamo.equals(that.fechaprestamo) : that.fechaprestamo != null)
            return false;
        if (fechadevolucion != null ? !fechadevolucion.equals(that.fechadevolucion) : that.fechadevolucion != null)
            return false;
        if (libro != null ? !libro.equals(that.libro) : that.libro != null) return false;
        if (usuario != null ? !usuario.equals(that.usuario) : that.usuario != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fechaprestamo != null ? fechaprestamo.hashCode() : 0;
        result = 31 * result + (fechadevolucion != null ? fechadevolucion.hashCode() : 0);
        result = 31 * result + (libro != null ? libro.hashCode() : 0);
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "libro", referencedColumnName = "isbn", nullable = false,
                insertable = false, updatable = false)
    public Librosv2 getLibroPrestado() {
        return libroPrestado;
    }

    public void setLibroPrestado(Librosv2 libroPrestado) {
        this.libroPrestado = libroPrestado;
    }

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
    public Usuariosv2 getPrestatario() {
        return prestatario;
    }

    public void setPrestatario(Usuariosv2 prestatario) {
        this.prestatario = prestatario;
    }
}
