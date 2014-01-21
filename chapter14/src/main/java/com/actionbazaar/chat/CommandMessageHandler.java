/**
 *  CommandMessage.java
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
import com.actionbazaar.chat.commands.CommandMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

/**
 * Handles command messages - actions to do things.
 * @author Ryan Cuprak
 */
public class CommandMessageHandler implements MessageHandler.Whole<AbstractCommand>  {

    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("ChatEndpointConfig");
    
    /**
     * Chat server
     */
    private final ChatServer chatServer;
    
    /**
     * Session
     */
    private final Session session;
    
    /**
     * Constructs a new ChatMessageHandler
     * @param chatServer - reference to the chat server
     * @param session - WebSocket session
     */
    public CommandMessageHandler(ChatServer chatServer, Session session) {
        this.chatServer = chatServer;
        this.session = session;
    }
    
    /**
     * Routes the message to the singleton bean
     * @param command - command to be performed
     */
    @Override
    public void onMessage(AbstractCommand command) {
        if(logger.isLoggable(Level.FINE)) {
            logger.log(Level.INFO, "Message recieved: {0}", command);
        }
        command.associateSession(session, chatServer);
        chatServer.performCommand(command);
    }
}
