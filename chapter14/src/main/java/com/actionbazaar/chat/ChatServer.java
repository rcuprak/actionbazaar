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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Logger;
import javax.ejb.Singleton;
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
    private static final Logger logger = Logger.getLogger("ChatService");
    
    /**
     * Support people with no one to talk to
     */
    private Stack<Session> availableReps = new Stack<Session>();
    
    /**
     * Clients with no one to talk to
     */
    private Stack<Session> waitingClients = new Stack<>();
    
    /**
     * Sessions
     */
    private Set<Session> csrSessions;
    
    /**
     * Client sessions
     */
    private Set<Session> clientSessions;
    
    /**
     * Conversations keyed by the csr session key
     */
    private Map<String,SupportConversation> conversations;
    
    /**
     * Constructs a new chat server
     */
    public ChatServer() {
        csrSessions = new HashSet<>();
        clientSessions = new HashSet<>();
        conversations = new HashMap<>();
    }
    
    /**
     * Registers a new customer service representative.
     * @param customerServicerep
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
     * @param session - session
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
        SupportConversation sc = conversations.get(clientSession.getId());
        sc.close();
        clientSessions.remove(sc.getCSRSession());
        csrSessions.remove(sc.getClientSession());
        conversations.remove(sc.getCSRSession().getId());
        conversations.remove(sc.getClientSession().getId());
    }
    
    /**
     * Handles a message
     * @param session - session
     * @param message - message
     */
    public void handleMessage(Session session, String message) {
        SupportConversation sc = conversations.get(session.getId());
        sc.deliverMessage(session.getId(), message); 
    }
    
    /**
     * Handles a close message
     * @param session - message
     */
    public void handleClose(Session session) {
        SupportConversation sc = conversations.get(session.getId());
        
    }
    
    /**
     * Connects a session - sends a command message over to both clients
     * @param client - client
     * @param csr = custom service rep
     */
    protected void connectSession(Session clientSession, Session csrSession) {
        SupportConversation sc = new SupportConversation(clientSession,csrSession);
        conversations.put(clientSession.getId(),sc);
        conversations.put(csrSession.getId(),sc);
        sc.connect();
    }
}
