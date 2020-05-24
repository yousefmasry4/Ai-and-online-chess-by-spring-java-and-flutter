package org.fdiez.websocket_demo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 public class db {

    public static void test(String mess) throws SQLException {
        Connection con;

        con = DriverManager.getConnection("jdbc:mysql://localhost/dev_db","root","admin");
        Statement stmt=con.createStatement();
        ResultSet rs = stmt.executeQuery("Show tables");
        System.out.println("Tables in the current database: ");
        while(rs.next()) {
            System.out.print(rs.getString(1));
            System.out.println();
        }
    }

}