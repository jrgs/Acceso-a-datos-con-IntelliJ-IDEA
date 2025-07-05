package org.example;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class Prestamosv2PK implements Serializable {
    private String libro;
    private String usuario;

    @Column(name = "libro", nullable = false, length = 13)
    @Id
    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    @Column(name = "usuario", nullable = false, length = 13)
    @Id
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

        Prestamosv2PK that = (Prestamosv2PK) o;

        if (libro != null ? !libro.equals(that.libro) : that.libro != null) return false;
        if (usuario != null ? !usuario.equals(that.usuario) : that.usuario != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = libro != null ? libro.hashCode() : 0;
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        return result;
    }
}
