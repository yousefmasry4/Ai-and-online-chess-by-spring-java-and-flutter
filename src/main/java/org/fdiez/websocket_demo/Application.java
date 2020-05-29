package org.fdiez.websocket_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class Application {
    static Connection con;
    static Statement stmt;
    public static void main(String[] args) throws SQLException {
         con = DriverManager.getConnection("jdbc:mysql://localhost/dev_db","root","admin");
        stmt = con.createStatement();
        SpringApplication.run(Application.class, args);
    }
}