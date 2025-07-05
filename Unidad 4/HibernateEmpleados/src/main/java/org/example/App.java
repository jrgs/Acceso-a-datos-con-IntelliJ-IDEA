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
public class App
{
    public static void main( String[] args )
    {
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

        //ListEmployees(session);
        //ListDepartments(session);
        ListEmployeesInDepartment(session, "Ventas");
    }

    private static void ListEmployees(Session session) {
        Query<ClaseEmpleados> myQuery = session.createQuery("from ClaseEmpleados");
        List<ClaseEmpleados> empleados = myQuery.list();

        for ( Object employeeObject : empleados ) {
            ClaseEmpleados empleado = (ClaseEmpleados) employeeObject;
            System.out.printf("Número : %d  Nombre: %s\n", empleado.getEmpno(), empleado.getNombre());
        }
    }

    private static void ListDepartments(Session session) {
        Query<ClaseDepartamentos> myQuery = session.createQuery("from ClaseDepartamentos ");
        List<ClaseDepartamentos> departamentos = myQuery.list();

        for ( Object departmentObject : departamentos ) {
            ClaseDepartamentos departamento = (ClaseDepartamentos) departmentObject;
            System.out.printf("Nombre: %s\n", departamento.getNombre());
            for ( ClaseEmpleados employee : departamento.getListaEmpleados())
                System.out.printf("\tNúmero : %d  Nombre: %s\n", employee.getEmpno(), employee.getNombre());
        }
    }

    private static void ListEmployeesInDepartment(Session session, String department) {
        Query<Empleado> myQuery = session.createQuery("" +
                "from Empleado e join fetch e.departamento d where d.nombre like  :paramDepartment");
        myQuery.setParameter("paramDepartment", department);

        List<Empleado> empleados = myQuery.list();

        System.out.println(department+":");
        for ( Object employeeObject : empleados ) {
            Empleado empleado = (Empleado) employeeObject;
            System.out.printf("\tNúmero : %d  Nombre: %s\n", empleado.getEmpno(), empleado.getNombre());
        }

    }
}
