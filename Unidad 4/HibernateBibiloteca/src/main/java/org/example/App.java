package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        //@SuppressWarnings("unused")
        //org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        //java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        if (session != null) {
            System.out.println("Sesión iniciada.");
        } else {
            System.out.println("Error abriendo la sesión.");
        }

        mostrarPrestamos(session);
        //ListDepartments(session);
    }

    private static void mostrarPrestamos(Session session) {
        Query<Prestamosv2> miQuery = session.createQuery("from ClasePrestamosv2");
        List<Prestamosv2> prestamos = miQuery.list();

        for (Object obj : prestamos) {
            Prestamosv2 prestamo = (Prestamosv2) obj;
            System.out.printf("Libro : %s  Usuario: %s\n", prestamo.getLibroPrestado().getTitulo(), prestamo.getPrestatario().getNombre());
        }
    }
}

