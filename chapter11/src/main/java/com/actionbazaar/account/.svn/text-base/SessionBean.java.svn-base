/**
 *  SessionBean.java
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
package com.actionbazaar.account;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * Session bean 
 * @author Ryan Cuprak
 */
@Named
@SessionScoped
public class SessionBean implements Serializable {
    
    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("SessionBean");
    
    /**
     * User service
     */
    @EJB
    private UserService userService;
   
    /**
     * Reference to the user
     */
    private User user;
    
    /**
     * Creates the current user
     * @return User
     */
    @Produces @SessionScoped @AuthenticatedUser @Named("currentUser")
    public User getCurrentUser() {
        if(user == null || user.isAnonymous()) {
            user = userService.getAuthenticatedUser();
        }
        return user;
    }
    
    /**
     * Returns true if the user has authenticated
     * @return true if authenticated
     */
    public boolean isAuthenticated() {
       return userService.isAuthenticated();
    }
    
        /**
     * Logs out the user
     * @param user - user
     */
    public void logout(@Disposes @AuthenticatedUser User user) {
        this.user = null;
    }
    
}
