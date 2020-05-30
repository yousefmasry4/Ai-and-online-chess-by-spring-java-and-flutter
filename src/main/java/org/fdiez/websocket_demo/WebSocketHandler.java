package org.fdiez.websocket_demo;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import javax.websocket.OnClose;
import java.awt.desktop.SystemSleepEvent;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;


public class WebSocketHandler extends AbstractWebSocketHandler {
    Dictionary<String, WebSocketSession> get_seession= new Hashtable<>();
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        var command=message.getPayload().toString().split(",");
        String game_id;
        System.out.println(message.getPayload().toString());
        switch (command[0]){
            case "get_id":
                System.out.println("case get id");
                if(session.isOpen()){
                    session.sendMessage(
                            //TODO: send the new row id ely hwa id el game ely hyt3ml fyh save le data el game
                            new TextMessage("c"+new GameHelper().id)
                    );
                }
                session.close(CloseStatus.GOING_AWAY);
                break;
            case "wait":
                System.out.println("wait");
                WebSocketSession player1=session;
                game_id=command[1];
                get_seession.put(session.getId(),session);
                //TODO: set player 1 of gem_id in db
                new GameHelper(game_id).player1.setID(player1.getId());
                break;
            case "getinto":
                game_id = command[1];
                System.out.println("case get into");
                boolean mawgod=GameHelper.id_is_here(Integer.parseInt(game_id));
                if(mawgod) {
                    //TODO get player1 id and put it in the next var
                    GameHelper g=new GameHelper(game_id);
                    String player1_id=g.player1.getId();
                    g.player2.setID(session.getId());
                    get_seession.put(session.getId(), session);
                    WebSocketSession player1_session = get_seession.get(player1_id);
                    session.sendMessage(new TextMessage("gogogo"));
                    player1_session.sendMessage(new TextMessage("gogogo"));
                }else{
                    session.sendMessage(new TextMessage("false"));
                }
                break;
            case "set_player1":
                System.out.println("case set 1");
                game_id=command[1];
                //TODO update player1 session id
                GameHelper g=new GameHelper(game_id);
                g.player1.setID(session.getId());
                get_seession.put(session.getId(),session);
                break;
            case "set_player2":
                System.out.println("case set 2");
                game_id=command[1];
                //TODO update player2 session id
                GameHelper gcase=new GameHelper(game_id);
                gcase.player2.setID(session.getId());
                get_seession.put(session.getId(),session);
                break;
            case "m":
                System.out.println("message");
                String peer_id=player.get_peer(session.getId());
                //String player=session.getId();
                System.out.println(session.getId());
                System.out.println(peer_id);
                //todo get peer id ya3ny 7tshof player dah player2 or 1 w tgeb ely byl3b m3ah w t7oto f next var
                get_seession.get(peer_id).sendMessage(message);
                break;

        }
         if(message.getPayload().toString().charAt(0) == 'e'){
            System.out.println("e");

            //todo tro7 el db tgeb el peer player ely m3ah zy ma kont shar7 mn shwaya w t7oto f next var
            String peer_id=player.get_peer(session.getId());
             System.out.println(session.getId());
             System.out.println(peer_id);
             System.out.println(get_seession.get(peer_id).getId());

             get_seession.get(peer_id).sendMessage(message);
        }


    }
    @OnClose
    public void onClose(WebSocketSession session) {
        get_seession.remove(session.getId());
    }
}
