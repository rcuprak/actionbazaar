/**
 *  ChatMessage.java
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
import javax.json.JsonReader;
import javax.json.stream.JsonGenerator;

/**
 * Represents a chat message
 * @author Ryan Cuprak
 */
public class ChatMessage extends AbstractCommand {

    /**
     * User
     */
    private String user;
    
    /**
     * Message
     */
    private String message;
    
    /**
     * Protected constructor- used by the decoder
     */
    protected ChatMessage() {
        super(CommandTypes.MESSAGE);
    }
    
    /**
     * Constructs a new chat message
     * @param user - user
     * @param message - user
     */
    public ChatMessage(String user, String message) {
        super(CommandTypes.MESSAGE);
        this.user = user;
        this.message = message;
    }
    
    /**
     * Returns the user
     * @return user
     */
    public String getUser() {
        return user;
    }
    
    /**
     * Returns the message
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Encodes a chat message
     * @param writer - JSON writer
     */
    @Override
    void encode(JsonGenerator writer) {
        writer.write("user",user);
        writer.write("message",message);
    }

    /**
     * Decodes a message
     * @param jsonObject - json object to be decoded 
     */
    @Override
    void decode(JsonObject jsonObject) {
        if(jsonObject.containsKey("user")) {
            user = jsonObject.getString("user");
        }    
        if(jsonObject.containsKey("message")) {
            message = jsonObject.getString("message");
        }
    }

    /**
     * Sends the message out.
     */
    @Override
    public void perform() {
        chatServer.sendMessage(session, message);
    }
}
