import java.sql.*;
import java.util.Scanner;

public class ej71 
{
    static final String url = "jdbc:postgresql://localhost:5432/Empleados";
    static final String user = "postgres";
    static final String password = "postgres";

    public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {
        try ( Connection conn = DriverManager.getConnection(url, user, password) ) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Introduzca el puesto: ");
            String puesto = sc.nextLine();
            CallableStatement statement = conn.prepareCall("{call lista_empleados_puesto('"+puesto+"')}");
            ResultSet rs = statement.executeQuery();
            System.out.println("Codigo Nombre"+ "\t\tDepartamento");
            System.out.println("-----------------------------------------");
            while (rs.next()) {
                System.out.printf("%6d %-15s %2d\n", rs.getInt(1),  rs.getString(2), rs.getInt(4));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
