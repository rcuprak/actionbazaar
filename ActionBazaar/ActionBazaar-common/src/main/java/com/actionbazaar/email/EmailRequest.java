/**
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
package com.actionbazaar.email;

import java.io.Serializable;
import java.util.Map;

/**
 * Email request to be sent out.
 * @author Ryan Cuprak
 */
public class EmailRequest implements Serializable {

    /**
     * Email
     */
    private String email;

    /**
     * Replacement tokens
     */
    private Map<String,String> replacementTokens;

    /**
     * Action
     */
    private String action;

    /**
     * Creates a new EmailRequest
     * @param email - email address
     * @param replacementTokens - replacement tokens
     * @param action - action
     */
    public EmailRequest(String email, Map<String,String> replacementTokens, String action) {
        this.email = email;
        this.replacementTokens = replacementTokens;
        this.action = action;
    }

    /**
     * Returns the email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the replacement tokens
     * @return replacement tokens
     */
    public Map<String, String> getReplacementTokens() {
        return replacementTokens;
    }

    /**
     * Returns the action
     * @return action
     */
    public String getAction() {
        return action;
    }
}
