package org.fdiez.websocket_demo;


import java.sql.ResultSet;
import java.sql.SQLException;

class player{
    int papaid;
    private String id;
    int n;
    // Getter
    public String getId() {
        return id;
    }

    // Setter
    public void setID(String newId) throws SQLException {
        this.id = newId;
        boolean t = Application.stmt.execute("UPDATE chess SET player"+n+"="+"'"+this.id+"'"+" WHERE id="+papaid+";");


    }
    player(String id,int n,String papa) throws SQLException {
        this.n=n;
        this.papaid=Integer.parseInt(papa);
        setID(id);
    }

    static String get_peer(String player_id) throws SQLException {
        String peer_id = null;

        ResultSet rsw = Application.stmt.executeQuery("SELECT  player1 ,player2 From chess WHERE  player1="+"'"+player_id+"'"+" or player2="+"'"+player_id+"'");
        while(rsw.next()) {
            String a=rsw.getString(1);
            String b=rsw.getString(2);
            peer_id= a.equals(player_id) ?b:a;
        }
        return peer_id;
    }
}



class GameHelper{
     String id;
     player player1;
     //ignore
     player player2;

    GameHelper(String id, player player1, player player2, player player21) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
    }
    GameHelper() throws SQLException {
        boolean t = Application.stmt.execute("INSERT INTO chess () VALUES();");
        ResultSet rs2 = Application.stmt.executeQuery("SELECT id, player1, player2 FROM chess ORDER BY id DESC LIMIT 0, 1;");
        int a = 0;
        String b = null,c = null;
        while(rs2.next()) {
            a=rs2.getInt(1);
            b=rs2.getString(2);
            c=rs2.getString(3);
        }
        this.id = Integer.toString(a);
        this.player1 =new player(b,1,id);
        this.player2 =new player(c,2,id);

    }
    GameHelper(String id) throws SQLException {
        ResultSet rs3 = Application.stmt.executeQuery("SELECT * FROM chess WHERE id="+Integer.parseInt(id));
        int a = 0;
        String b = null,c = null;
        while(rs3.next()) {
             a=rs3.getInt(1);
             b=rs3.getString(2);
             c=rs3.getString(3);
        }
        this.id = Integer.toString(a);
        this.player1 =new player(b,1,id);
        this.player2 =new player(c,2,id);
    }
    static boolean id_is_here(int id) throws SQLException {
        ResultSet result = Application.stmt.executeQuery("select id from chess where id="+id+";");
        if(!result.isBeforeFirst()){
            return false;
        }
        else{
            return true;
        }
    }
}