/**
 *  ChatService.java
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

import com.actionbazaar.chat.commands.AbstractCommand;
import com.actionbazaar.chat.commands.ChatMessage;
import com.actionbazaar.chat.commands.CommandTypes;
import com.actionbazaar.chat.commands.CommandMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.websocket.CloseReason;
import javax.websocket.Session;

/**
 * Responsible for associating chats with authenticated users
 * @author Ryan Cuprak
 */
@Singleton
public class ChatServer {
    
    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("ChatServer");
    
    /**
     * Support people with no one to talk to
     */
    private final Stack<Session> availableReps = new Stack<>();
    
    /**
     * Clients with no one to talk to
     */
    private final Stack<Session> waitingClients = new Stack<>();
    
    /**
     * Sessions
     */
    private final Set<Session> csrSessions;
    
    /**
     * Client sessions
     */
    private final Set<Session> clientSessions;
    
    /**
     * Conversations keyed by the csr session key
     */
    private final Map<String,SupportConversation> conversations;
    
    /**
     * Constructs a new chat server
     */
    public ChatServer() {
        csrSessions = new HashSet<>();
        clientSessions = new HashSet<>();
        conversations = new HashMap<>();
    }
    
    /**
     * Returns session statistics
     */
    public void getSessionStats() {
        
    }
    
    /**
     * Registers a new customer service representative.
     * @param csrSession - custom service representative
     */
    public void customServiceRepConnected(Session csrSession) { 
        csrSessions.add(csrSession);
        if(!waitingClients.empty()) {
            Session clientSession = waitingClients.pop();
            connectSession(clientSession,csrSession); 
        } else {
            availableReps.push(csrSession);
        }
    }
    
    /**
     * Disconnects a session
     * @param csrSession - custom service representative disconnected
     */
    public void customServiceRepDisconnected(Session csrSession) {
        csrSessions.remove(csrSession);
    }
    
    /**
     * Adds a client session
     * @param clientSession - client session
     */
    public void addClientSession(Session clientSession) {
        clientSessions.add(clientSession);
        if(!availableReps.empty()) {
            Session repSession = availableReps.pop();
            connectSession(clientSession,repSession); 
        } else {
            waitingClients.push(clientSession);
        }
    }
    
    /**
     * Closes out a session
     * @param clientSession - client sessions
     */
    public void removeClientSession(Session clientSession) {
        logger.info("Closing client session.");
        SupportConversation sc = conversations.get(clientSession.getId());
        clientSessions.remove(sc.getCSRSession());
        csrSessions.remove(sc.getClientSession());
        conversations.remove(sc.getCSRSession().getId());
        conversations.remove(sc.getClientSession().getId());
        // todo - send message to the csr that the session has ended. 
        sc.getCSRSession().getAsyncRemote().sendObject(new CommandMessage(CommandTypes.CLIENT_LOST));
        customServiceRepConnected(sc.getCSRSession()); 
    }
    
    /**
     * Handles a message
     * @param sourceSession - session sending the messages
     * @param message - text to be send
     */
    public void sendMessage(Session sourceSession, String message) {
        SupportConversation sc = conversations.get(sourceSession.getId());
        if(sc != null) {
            sc.deliverMessage(sourceSession.getId(), message);
        } else {
            // the other party isn't there anymore...
            sourceSession.getAsyncRemote().sendObject(new ChatMessage("server","Chat session counterpart is missing."));
        }
    }
    
    /**
     * Handles a close message
     * @param session - message
     */
    public void handleClose(Session session) {
        // Loss of a customer service representative, put the client back in the pool to get a new CSR
        if(csrSessions.contains(session)) {
            csrSessions.remove(session);
            SupportConversation sc = conversations.get(session.getId());
            if(sc != null) {
                sc.getClientSession();
                conversations.remove(sc.getClientSession().getId());
                sc.getClientSession().getAsyncRemote().sendObject(new CommandMessage(CommandTypes.CSR_LOST));
                waitingClients.push(sc.getClientSession());
            }
        }
    }
    
    /**
     * Connects a session - sends a command message over to both clients
     * @param clientSession - client session
     * @param csrSession - csr session
     */
    protected void connectSession(Session clientSession, Session csrSession) {
        SupportConversation sc = new SupportConversation(clientSession,csrSession);
        conversations.put(clientSession.getId(),sc);
        conversations.put(csrSession.getId(),sc);
        sc.connect();
    }
    
    /**
     * Invoked by the context listener when the server is shutting down.
     */
    public void shutdown() {
        logger.info("Shutdown requested - terminating outstanding conversations.");
        for(Session session : csrSessions) {
            try { 
                session.close(new CloseReason(CloseReason.CloseCodes.GOING_AWAY,
                        "Server shutdown"));
            } catch (IOException e) {
                logger.log(Level.SEVERE,"Error closing connection",e);
            }
        }
        for(Session session : clientSessions) {
            try { 
                session.close(new CloseReason(CloseReason.CloseCodes.GOING_AWAY,
                        "Server shutdown"));
            } catch (IOException e) {
                logger.log(Level.SEVERE,"Error closing connection",e);
            }  
        }
    }
    
    /**
     * Command that is to be performed
     * @param command - command to be performed
     */
    public void performCommand(AbstractCommand command) {
        command.perform();
    }
}
