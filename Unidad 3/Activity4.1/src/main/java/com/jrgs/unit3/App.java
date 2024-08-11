package com.jrgs.unit3;

import java.sql.*;
import java.util.Scanner;

/**
 * The type App.
 */
public class App {
    /**
     * The constant scanner.
     */
    protected static Scanner scanner = new Scanner(System.in);
    /**
     * The constant con.
     */
    protected static Connection con = null;
    /**
     * The constant rs.
     */
    protected static ResultSet rs = null;

    /**
     * Shows a menu to choose an option
     *
     * @return the selected option
     */
    protected static int menu() {
        System.out.println("MENU");
        System.out.println("====");
        System.out.println();
        System.out.println("1.- Display subject list.");
        System.out.println("2.- Add a subject.");
        System.out.println("3.- Alter table.");
        System.out.println("0.- Terminate.");

        while ( !scanner.hasNextInt() ) {
            System.out.println("Choose an option between 0 and 3, please.");
            scanner.next();
        }

        return( scanner.nextInt() );
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws ClassNotFoundException the class not found exception
     * @throws SQLException           the sql exception
     */
    public static void main(String[] args)
            throws ClassNotFoundException, SQLException {
         try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/VTInstitute";
            String user = "postgres";
            String password = "postgres";
            con = DriverManager.getConnection(url, user, password);
            int option = menu();
            while ( option != 0 ) {
                if (option == 1)
                    DisplaySubjectList();
                else if (option == 2)
                    AddSubject();
                else if (option == 3)
                    AlterTable();
                option = menu();
            }
         }
        catch( SQLException e ) {
            System.out.printf("SQL error. %s\n", e.getMessage());
        }
        catch ( ClassNotFoundException e ) {
            System.out.printf("Driver error: %s\n", e.getMessage());
        }
        finally {
            if ( rs!=null ) rs.close();
            if ( con!=null ) con.close();
        }
    }

    private static void AlterTable() throws SQLException {
        Statement statement = con.createStatement();
        String SQLsentence = "ALTER TABLE subjects ADD COLUMN hours INTEGER";
        int rows = statement.executeUpdate(SQLsentence.toString(), Statement.RETURN_GENERATED_KEYS);
        System.out.printf("Rows affected: %d\n", rows);
    }

    private static void AddSubject() throws SQLException {
        if (scanner.hasNextLine()) scanner.nextLine();
        System.out.print("Subject name? ");
        String subjectName = scanner.nextLine();
        System.out.println();
        System.out.print("Subject year? ");
        int subjectYear = scanner.nextInt();
        System.out.print("Course code? ");
        int courseCode = scanner.nextInt();
        System.out.println();
        Statement statement = con.createStatement();
        StringBuilder SQLsentence = new StringBuilder();
        SQLsentence.append("INSERT INTO subjects VAlUES ");
        SQLsentence.append("(DEFAULT, '");
        SQLsentence.append(subjectName);
        SQLsentence.append("', '");
        SQLsentence.append(subjectYear);
        SQLsentence.append("', '");
        SQLsentence.append(courseCode);
        SQLsentence.append("');");
        int rows = statement.executeUpdate(SQLsentence.toString(), Statement.RETURN_GENERATED_KEYS);
        System.out.printf("Rows inserted: %d\n", rows);
    }

    private static void DisplaySubjectList() throws SQLException {
        Statement statement = con.createStatement();
        String SQLsentence = "SELECT * FROM subjects ORDER BY code";
        rs = statement.executeQuery(SQLsentence);
        System.out.println("Code" + "\t" + "Name" + "\t" + "Year");
        System.out.println("-----------------------------------------");
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t " +
                    rs.getString(2) + " (" + rs.getString(3)+")");
        }
    }
}