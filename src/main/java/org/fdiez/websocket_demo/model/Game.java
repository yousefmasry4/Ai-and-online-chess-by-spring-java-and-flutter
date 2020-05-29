package org.fdiez.websocket_demo;


import org.fdiez.websocket_demo.db.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.sql.*;
import java.util.ArrayList;

public class Game {
    private int ID;

    boolean t;//to accept the return of the sqlQuery
    String test1;//to test if the arrayPlayer is null or not
    String test2;//to test if the arrayPlayer is null or not
    ResultSet rs;
    public Game() throws SQLException {
//        int test = Application.stmt.executeUpdate("INSERT INTO chessgame () VALUES();");
//        System.out.println("test = " + test);
        t = Application.stmt.execute("INSERT INTO chess () VALUES();");
        rs = Application.stmt.executeQuery("SELECT id, arrayPlayer1, arrayPlayer2 FROM chess ORDER BY id DESC LIMIT 0, 1;");
        while(rs.next()) {
            ID = rs.getInt(1);
           test1 = rs.getString(2);
            test2 = rs.getString(3);
        }
        System.out.println("ID = " + ID);

        //This is used to initialize the arrayPlayer with space in order to be able to append movements of players later
        if(test1 == null)
        {
            t = Application.stmt.execute("UPDATE chess SET arrayPlayer1 = ' ' WHERE id = + "+ ID +";");
        }
        if(test2 == null)
        {
            t = Application.stmt.execute("UPDATE chess SET arrayPlayer2 = ' ' WHERE id = + "+ ID +";");
        }
        //database tests

      /*String id = "test1";
        int gameID = 8;
        t = Application.stmt.execute("UPDATE chessgame SET player1 = '"+ id +"' WHERE id = + "+ gameID +";");
        t = Application.stmt.execute("UPDATE `dev_db`.`chessgame` SET `player1` = 'player1' WHERE (`id` = " + gameID + ");");
        test = rs.getString(1);
        rs = Application.stmt.executeQuery("SELECT player2 FROM chessgame WHERE id = 2;");
        System.out.println("Tables in the current database: ");*/


    }


    public int getID() {
        return ID;
    }

    //player here is either 1 or 2 to determine which player to write id for in database
    public player setPlayer(String ID, int player) throws SQLException{
        player p = new player(ID);
        int gameID = getID();
        switch (player){
            case 1:
                t = Application.stmt.execute("UPDATE chess SET player1 = '"+ ID +"' WHERE id = + "+ gameID +";");
                p.play(" test1", 1, gameID);
                break;
            case 2:
                t = Application.stmt.execute("UPDATE chess SET player2 = '"+ ID +"' WHERE id = + "+ gameID +";");
                p.play(" test2", 2, gameID);
                break;
        }

        return p;
    }

    class player
    {
        String ID;
        ArrayList<String> Movements = new ArrayList<>();

        player(String ID){
            this.ID = ID;
        }

        void play(String movement, int player, int gameID) throws SQLException{
            Movements.add(movement);
            switch (player){
                case 1:
                    t = Application.stmt.execute("UPDATE chess SET arrayPLayer1 = CONCAT(arrayPLayer1, '" + movement + "') " +"WHERE id = " + gameID + ";");
                    break;
                case 2:
                    t = Application.stmt.execute("UPDATE chess SET arrayPLayer2 = CONCAT(arrayPLayer2, '" + movement + "') " +"WHERE id = " + gameID + ";");
                    break;
            }
        }

    }
}
