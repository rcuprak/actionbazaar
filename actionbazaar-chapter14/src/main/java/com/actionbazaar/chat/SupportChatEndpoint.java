/**
 * SupportChat.java
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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;

/**
 * Chat Support - user must be in the CSR role
 * @author Ryan Cuprak
 */
public class SupportChatEndpoint extends Endpoint {
   
    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("ChatSupport");
    
    /**
     * Chat server
     */
    @EJB
    private ChatServer chatServer;   

    /**
     * Opens the connection
     * @param session - session
     * @param config - endpoint configuration
     */
    @Override
    public void onOpen(Session session, EndpointConfig config) {
        chatServer.customServiceRepConnected(session);
        session.addMessageHandler(new CommandMessageHandler(chatServer,session));
    }
    
    @Override
    public void onError(Session session, Throwable thr) {
        chatServer.customServiceRepDisconnected(session);
        try {
            session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION,thr.getMessage()));
        } catch (IOException ex) {
            Logger.getLogger(SupportChatEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        chatServer.customServiceRepDisconnected(session);
    } 
}
