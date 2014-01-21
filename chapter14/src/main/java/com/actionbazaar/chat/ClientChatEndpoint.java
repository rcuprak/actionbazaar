/**
 *  ChatServer.java
 *  EJB 3 in Action
 *  Book: http://manning.com/panda2/
 *  Code: http://code.google.com/p/action-bazaar/
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.actionbazaar.chat;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Chat server
 * @author Ryan Cuprak
 */
@Stateless
@ServerEndpoint(value="/chat",encoders = {MessageEncoder.class,CommandEncoder.class})
public class ClientChat implements Serializable {
    
    /**
     * Serial UID
     */
    private static final long serialVersionUID = -821556242107966141L;
    
     /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("ChatService");
    
    /**
     * Reference to the chat server
     */
    @Inject
    private ChatServer chatServer;
    
    /**
     * Registers the session with the chat server 
     * @param session
     * @param conf 
     */
    @OnOpen
    public void open(Session session, EndpointConfig conf) { 
        logger.log(Level.INFO, "Connection opened: {0}", session.getId());
        chatServer.addClientSession(session);
    }
    
    /**
     * Sends a message 
     * @return Text to echo
     */
    @OnMessage
    public String sendMessage(Session session, String message) {
        logger.log(Level.INFO, "Got message: {0}", session.getUserPrincipal());
        chatServer.handleMessage(session, message);
        return message;
    }
    
    /**
     * Connection closed
     * @param session 
     */
    @OnClose
    public void close(Session session) {
        chatServer.removeClientSession(session);
    }
    
    /**
     * Handles errors
     * @param session
     * @param error 
     */
    @OnError
    public void error(Session session, Throwable error) { 
        logger.log(Level.INFO, "Handling errors: {0}", error.getMessage());
        chatServer.removeClientSession(session);
    }
}
