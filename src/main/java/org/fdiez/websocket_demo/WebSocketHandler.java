package org.fdiez.websocket_demo;

import org.fdiez.websocket_demo.db.db;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WebSocketHandler extends AbstractWebSocketHandler {
    List<WebSocketSession> list=new ArrayList<WebSocketSession>();
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if(!list.contains(session))
        list.add(session);

        System.out.println("New Simple Message Received");
        list.forEach((e)->{
            try {
                if(e.isOpen() && e != session)
                e.sendMessage(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
        db.test(message.toString());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("New Text Message Received "+message.getPayload());
        session.sendMessage(new TextMessage("got this for you: "+message.getPayload()));
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
        System.out.println("New Binary Message Received");
        session.sendMessage(message);
    }
}
