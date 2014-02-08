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
package com.actionbazaar.chat.commands;

import com.actionbazaar.chat.ChatServer;
import java.util.HashMap;
import java.util.Map;
import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;

/**
 * Represents a command - sent by either the client or the server.
 * @author Ryan Cuprak
 */
public class CommandMessage extends AbstractCommand {
    
    /**
     * Parameters for the command
     */
    private Map<String,String> parameters = new HashMap<>();
    
    /**
     * Invoked by the decoder
     */
    protected CommandMessage() {
        super(null);
    }
    
    /**
     * Constructs a new no parameter command message
     * @param command - command
     */
    public CommandMessage(CommandTypes command) {
        super(command);
    }
    
    /**
     * Initializes a command message
     * @param command - message
     * @param parameters - parameters
     */
    public CommandMessage(CommandTypes command,Map<String,String> parameters) {
        super(command);
        this.parameters = parameters;
    }
    
    /**
     * Returns the parameters for the command
     * @return parameters
     */
    public Map<String,String> getParameters() {
        return parameters;
    }  

    /**
     * Writes out the entries
     * @param writer 
     */
    @Override
    void encode(JsonGenerator writer) {
        for(Map.Entry<String,String> entry : parameters.entrySet()) {
            writer.write(entry.getKey(),entry.getValue());
        }
    }

    /**
     * Decodes the incoming message
     * @param jsonObject - json object
     */
    @Override
    void decode(JsonObject jsonObject) {
        for(String key : jsonObject.keySet()) {
            if(!key.equals(("type"))) {
                parameters.put(key,jsonObject.getString(key));
            } else {
                setCommandType(CommandTypes.valueOf(jsonObject.getString(key)));
            }
        }
    }

    /**
     * Performs the requested command.
     */
    @Override
    public void perform() {
        switch(getCommand()) {
             
        }
    }
}
