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

import java.util.HashMap;
import java.util.Map;

/**
 * Command Message
 * @author Ryan Cuprak
 */
public class CommandMessage {
    
    /**
     * Command
     */
    private CommandType command;
    
    /**
     * Parameters for the command
     */
    private Map<String,String> parameters = new HashMap<>();
    
    /**
     * Constructs a new command message
     * @param command - command message
     */
    public CommandMessage(CommandType command, Map<String,String> parameters) {
        this.command = command;
        this.parameters = parameters;
    }
    
    /**
     * Returns the command
     * @return command
     */
    public CommandType getCommand() {
        return command;
    }
    
    /**
     * Returns the parameters for the command
     * @return parameters
     */
    public Map<String,String> getParameters() {
        return parameters;
    }
}
