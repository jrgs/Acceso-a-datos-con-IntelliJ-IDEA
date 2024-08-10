package com.jrgs.aadl.libro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args)
            throws SQLException {
        String url = "jdbc:postgresql://192.168.224.125:5432/InstitutoFP";
        String user = "postgres";
        String password = "postgres";
        Connection con = DriverManager.getConnection(url, user, password);
        Statement statement = con.createStatement();
        String SQLsentence = "SELECT * FROM asignaturas ORDER BY codigo";
        ResultSet rs = statement.executeQuery(SQLsentence);
        System.out.println("Código" + "\t" + "Nombre");
        System.out.println("-------------------------------------");
        while (rs.next()) {
            //System.out.println(rs.getString(1) + "\t" + rs.getString(2));
            System.out.println(rs.getString("codigo") + "\t" + rs.getString("nombre"));
        }
        rs.close();
        con.close();
    }
}
