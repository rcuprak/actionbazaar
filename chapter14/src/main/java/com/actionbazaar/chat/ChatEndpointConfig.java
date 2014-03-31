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

import com.actionbazaar.chat.commands.CommandMessageDecoder;
import com.actionbazaar.chat.commands.CommandMessageEncoder;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.websocket.Decoder;
import javax.websocket.Encoder;
import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

/**
 * Constructs the chat endpoint.
 * @author Ryan Cuprak
 */
public class ChatEndpointConfig implements ServerApplicationConfig {

    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("ChatEndpointConfig");
    
    public ChatEndpointConfig() {
        logger.info("ChatEndpointConfig instantiated...");
    }
    
    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> endpointClasses) {
        logger.info("Checking the endpoint...");
        // Configuring the decoders
        List<Class<? extends Decoder>> decoders = new LinkedList<>();
        decoders.add(CommandMessageDecoder.class);
        // Configuring the encoders
        List<Class<? extends Encoder>> encoders = new LinkedList<>();
        encoders.add(CommandMessageEncoder.class);
        Set<ServerEndpointConfig> configs = new HashSet<>();
        configs.add(ServerEndpointConfig.Builder.create(ClientChatEndpoint.class, "/chat").decoders(decoders).encoders(encoders).build());
        configs.add(ServerEndpointConfig.Builder.create(SupportChatEndpoint.class, "/support").decoders(decoders).encoders(encoders).build());
        return configs;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        return scanned;
    }
    
}
