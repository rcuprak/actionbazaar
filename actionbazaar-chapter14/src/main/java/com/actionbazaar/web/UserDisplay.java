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
package com.actionbazaar.web;

import com.actionbazaar.account.AnonymousUser;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.actionbazaar.account.User;
import com.actionbazaar.account.UserService;

/**
 * Displays the current logged in user
 * @author Ryan Cuprak
 */
@Named
@SessionScoped
public class UserDisplay implements Serializable {

    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("UserDisplay");
    
    /**
     * Serial UID
     */
    private static final long serialVersionUID = 2080223429107249619L;
    
    /**
     * User
     */
    private User user;

    /**
     * Checks credentials - if we have logged in, th
     */
    private synchronized void checkCredentials() {
        if(user != null && !user.isGuest()) {
            return;
        }
        String remoteUser = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        if(remoteUser != null) {
            Context ctx = null;
            try {
                ctx = new InitialContext();
                UserService us = (UserService)ctx.lookup("java:global/chapter14/UserServiceBean");
                user = us.getUser(remoteUser);
            } catch (NamingException e) {
                logger.log(Level.SEVERE,"Unable to retrieve the UserServiceBean.",e);
            } finally {
                if(ctx != null) try { ctx.close(); } catch (Throwable t) {}    
            }
        } else {
            instantiateGuestInstance();
        }
    }

    /**
     * Instantiates a guest instance
     */
    private void instantiateGuestInstance() {
        user = new AnonymousUser();
        user.setGuest(true);
    }

    /**
     * Returns the current user
     * @return user
     */
    public User getUser() {
        checkCredentials();
        return user;
    }

    /**
     * Returns true if we are a guest
     * @return true if guest
     */
    public boolean isGuest() {
        checkCredentials();
        return user == null || user.isGuest();
    }

    /**
     * Invalidates the session
     */
    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        instantiateGuestInstance();
    }
}
