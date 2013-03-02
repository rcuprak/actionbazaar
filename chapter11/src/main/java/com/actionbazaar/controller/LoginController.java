/**
 *  LoginController.java
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
package com.actionbazaar.controller;

import com.actionbazaar.NavigationRules;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Authenticates 
 * @author Ryan Cuprak
 */
@Model
public class LoginController {
    
    /**
     * Faces context
     */
    @Inject
    private FacesContext context;
    
    /**
     * Username
     */
    private String username;
    
    /**
     * Password
     */
    private String password;

    /**
     * Returns the username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username
     * @param username - username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password
     * @param password - password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Performs authentication
     * @return next page
     */
    public String authenticate() {
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        try {
            request.login(username, password);
            Logger.getLogger("LoginTest").log(Level.INFO, "Got username: {0}", username);
        } catch (Throwable t) {
            return null;
        }
        return NavigationRules.HOME.getRule();
    }
}
