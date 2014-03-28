/**
 *  CommandDecoder.java
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

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Decodes a command
 * @author Ryan Cuprak
 */
public class CommandMessageDecoder implements Decoder.Text<AbstractCommand> {

    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("ChatEndpointConfig");
   
    /**
     * Initializes the decoder
     * @param config - endpoint configuration
     */
    @Override
    public void init(EndpointConfig config) {
        // do nothing 
    }
    
    /**
     * Decodes the message
     * @param message
     * @return
     * @throws DecodeException 
     */
    @Override
    public AbstractCommand decode(String message) throws DecodeException {
       logger.log(Level.INFO, "Decoding: {0}", message);
       JsonObject struct;
       try (JsonReader rdr = Json.createReader(new StringReader(message))) {
            struct = rdr.readObject();
       }   
       String type = struct.getString("type");
       CommandTypes cmdType = CommandTypes.valueOf(type);
        try {
            AbstractCommand cmd = (AbstractCommand)cmdType.getCommandClass().newInstance();
            cmd.decode(struct);
            return cmd;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CommandMessageDecoder.class.getName()).log(Level.SEVERE, null, ex);
            throw new DecodeException(message,"Could not be decoded - invalid type.");
        }
    }

    /**
     * Returns true if the message is a command
     * @param message - contents of the message
     * @return true if we can decode this message
     */
    @Override
    public boolean willDecode(String message) {
        JsonObject struct;
        try (JsonReader rdr = Json.createReader(new StringReader(message))) {
            struct = rdr.readObject();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void destroy() {
        // destroyed!
    }
    
}
