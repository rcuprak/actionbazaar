/**
 *  Bootstrap.java
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
package com.actionbazaar.chat.bulletin;

import java.io.Serializable;
import javax.websocket.Session;

/**
 *
 * @author Ryan Cuprak
 */
public class CustomServiceRepEvent implements Serializable {
    
    /**
     * Serial UID
     */
    private static final long serialVersionUID = 8381943445502799050L;
    
    /**
     * Customer service representative
     */
    private Session session;
    
    /**
     * Creates a new customer service 
     * @param session 
     */
    public CustomServiceRepEvent(Session session) {
        this.session = session;
    }
    
    /**
     * Returns the session
     * @return session
     */
    public Session getSession() {
        return session;
    }
    
}
