/**
 *  SupportConversation.java
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

import com.actionbazaar.chat.commands.CommandTypes;
import com.actionbazaar.chat.commands.CommandMessage;
import com.actionbazaar.chat.commands.ChatMessage;
import com.actionbazaar.account.Employee;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;

/**
 * Represents a support conversation
 * @author Ryan Cuprak
 */
public class SupportConversation implements Serializable {
    
    /**
     * Serial UID
     */
    private static final long serialVersionUID = 4756864946060077307L;
   
    /**
     * Employee answering questions
     */
    private Employee employee;
    
    /**
     * Session
     */
    private Session clientSession;
    
    /**
     * Employee session
     */
    private Session csrSession;
    
    /**
     * Support conversation
     * @param clientSession - client session
     * @param csrSession - customer service session
     */
    public SupportConversation(Session clientSession, Session csrSession) {
        this.clientSession = clientSession;
        this.csrSession = csrSession;
    }
    
    /**
     * Sends an introduction to each person in the conversation.
     */
    public void connect() {
        Map<String,String> parameters = new HashMap<>();
        if(clientSession.getUserPrincipal() == null) {
            parameters.put("message","You are talking to a guest.");
        } else {
            parameters.put("message","You are talking to " + clientSession.getUserPrincipal());
        } 
        CommandMessage introForCsr = new CommandMessage(CommandTypes.CONNECT,parameters);
        parameters = new HashMap<>();
        parameters.put("message","You are talking to " + csrSession.getUserPrincipal());
        CommandMessage introForClient = new CommandMessage(CommandTypes.CONNECT,parameters);
        clientSession.getAsyncRemote().sendObject(introForClient);
        csrSession.getAsyncRemote().sendObject(introForCsr);
    }
    
    /**
     * Delivers a message to the other party
     * @param id - message id
     * @param message - message to be sent
     */
    public void deliverMessage(String id, String message) {
        String username;
        if(clientSession.getId().equals(id)) {
            if(clientSession.getUserPrincipal() == null) {
                username = "Guest";
            } else {
                username = clientSession.getUserPrincipal().getName();
            }
        } else {
            username = csrSession.getUserPrincipal().getName();
        }
        ChatMessage cm = new ChatMessage(username,message);
        csrSession.getAsyncRemote().sendObject(cm);
        clientSession.getAsyncRemote().sendObject(cm);
    }
    
    /**
     * Returns the client session
     * @return client session
     */
    public Session getClientSession() {
        return clientSession;
    }
    
    /**
     * Returns the CSR session
     * @return csr session
     */
    public Session getCSRSession() {
        return csrSession;
    }
}
