package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "libros", schema = "public", catalog = "Biblioteca")
public class ClaseLibrosMN {
    private String isbn;
    private String titulo;
    private Integer copias;
    private String editorial;
    private List<ClasePrestamos> prestadoA;

    @Id
    @Column(name = "isbn", nullable = false, length = 13)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "titulo", nullable = false, length = 90)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name = "copias", nullable = true)
    public Integer getCopias() {
        return copias;
    }

    public void setCopias(Integer copias) {
        this.copias = copias;
    }

    @Basic
    @Column(name = "editorial", nullable = true, length = 60)
    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClaseLibrosMN that = (ClaseLibrosMN) o;

        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (titulo != null ? !titulo.equals(that.titulo) : that.titulo != null) return false;
        if (copias != null ? !copias.equals(that.copias) : that.copias != null) return false;
        if (editorial != null ? !editorial.equals(that.editorial) : that.editorial != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = isbn != null ? isbn.hashCode() : 0;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (copias != null ? copias.hashCode() : 0);
        result = 31 * result + (editorial != null ? editorial.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "libro")
    public List<ClasePrestamos> getPrestadoA() {
        return prestadoA;
    }

    public void setPrestadoA(List<ClasePrestamos> prestadoA) {
        this.prestadoA = prestadoA;
    }
}
