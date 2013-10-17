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
package com.actionbazaar.chat;

/**
 * Represents a chat message
 * @author Ryan Cuprak
 */
public class ChatMessage {

    /**
     * User
     */
    private String user;
    
    /**
     * Message
     */
    private String message;
    
    /**
     * Constructs a new chat message
     * @param user - user
     * @param message - user
     */
    public ChatMessage(String user, String message) {
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
}
