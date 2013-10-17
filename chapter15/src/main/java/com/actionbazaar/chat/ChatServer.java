/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.actionbazaar.chat;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;
import javax.websocket.WebSocketMessage;
import javax.websocket.WebSocketOpen;
import javax.websocket.server.WebSocketEndpoint;

/**
 *
 * @author rcuprak
 */
@WebSocketEndpoint("/chat")
public class ChatServer {
    
    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("ChatServer");
    
    /**
     * Start communication
     */
    @WebSocketOpen
    public void communicationStart() {
        logger.info("Connection has been established.");
    }
    
    @WebSocketMessage
    public void sendMessage(String message, Session session) {
       logger.log(Level.INFO, "We''ve got a message: {0}", message);
    }
    
}
