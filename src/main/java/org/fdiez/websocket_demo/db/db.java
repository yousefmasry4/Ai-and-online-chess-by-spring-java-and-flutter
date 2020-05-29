package org.fdiez.websocket_demo.db;

import org.fdiez.websocket_demo.Game;

import java.sql.SQLException;

 public class db {

    public static void test(String mess) throws SQLException {
        //database tests

        /*Connection con;
        con = DriverManager.getConnection("jdbc:mysql://localhost/dev_db","root","root");
        Statement stmt=con.createStatement();
       ResultSet rs = stmt.executeQuery("Show tables");
        System.out.println("Tables in the current database: ");
        while(rs.next()) {
            System.out.print(rs.getString());
            System.out.println();
        }
        boolean t = stmt.execute("INSERT INTO test () VALUES();");*/

        //class tests

        Game g = new Game();
        g.setPlayer("mefla7_1", 1);
        g.setPlayer("mefla7_2", 2);
    }

}