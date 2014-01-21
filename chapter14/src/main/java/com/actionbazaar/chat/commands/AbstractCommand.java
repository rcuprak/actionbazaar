/**
 *  AbstractCommand.java
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
package com.actionbazaar.chat.commands;

import com.actionbazaar.chat.ChatServer;
import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;
import javax.websocket.Session;

/**
 * Represents an abstract command.
 * @author Ryan Cuprak
 */
public abstract class AbstractCommand {
    
     /**
     * Command
     */
    private CommandTypes commandType;
    
    /**
     * Session
     */
    protected Session session;
    
    /**
     * Chat server
     */
    protected ChatServer chatServer;
    
    /**
     * Initializes a command message
     * @param command - message
     */
    public AbstractCommand(CommandTypes command) {
        this.commandType = command;
    }
    
    /**
     * Returns the command - used to determine which class to instantiate
     * @return command
     */
    public CommandTypes getCommand() {
        return commandType;
    }   
    
    /**
     * Sets the command type
     * @param commandType - command type
     */
    protected void setCommandType(CommandTypes commandType) {
        this.commandType = commandType;
    }
    
    /**
     * Associates this command with the server context
     * @param session - session to which this command was issued
     * @param chatServer - chat server
     */
    public void associateSession(Session session, ChatServer chatServer) {
        this.session = session;
        this.chatServer = chatServer;
    }
    
    /**
     * Encodes a message
     * @param writer - JSON Generator
     */
    abstract void encode(JsonGenerator writer);
    
    /**
     * Decodes a message
     * @param reader - JSON reader
     */
    abstract void decode(JsonObject jsonObject);
    
    /**
     * Invoked to perform the command
     */
    public abstract void perform();
   
}
