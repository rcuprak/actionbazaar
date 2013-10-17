/**
 *  UserServiceBean.java
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

import java.security.Principal;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Users Service Bean
 */
@Stateless
public class UserServiceBean implements UserService {
    
    /**
     * User Service Bean
     */
    private static final Logger logger = Logger.getLogger("UserServiceBean");
    
    /**
     * Persistence Context
     */
    @PersistenceContext(unitName="actionbazaar")
    private EntityManager entityManager;
    
    /**
     * EJB Session Context
     */
    @Resource
    private SessionContext context;
    
    /**
     * Retrieves a bidder from the database
     * @param userId - user id
     * @return User
     */
    @Override
    public User getUser(long userId) {
        return entityManager.find(BazaarAccount.class,userId);
    }

    /**
     * Retrieves the user given the username
     * @param username - username
     * @return User
     */
    @Override
    public User getUser(String username) {
        TypedQuery<User> q = entityManager.createQuery("select u from User u where u.username = :username",User.class);
        q.setParameter("username",username);
        List<User> users = q.getResultList();
        if(users.size() == 1) {
            return users.get(0);
        } else if (users.size() > 1) {
            throw new RuntimeException("Username should be unique.");
        }
        return null;
    }

    /**
     * Creates a new user in the system
     * @param user - user
     */
    @Override
    public void createUser(User user) {
        String uid = UUID.randomUUID().toString();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        entityManager.persist(user);
    }
    
     /**
     * Returns the current user
     * @return User
     */
    @Override
    public User getAuthenticatedUser() {
        Principal principal = context.getCallerPrincipal();
        User user;
        String name = principal.getName();
        if(name.equals("ANONYMOUS")) {
            user = new AnonymousUser();
        } else {
            user = this.getUser(name);
            if(context.isCallerInRole("CSR")) {
                // caller is in CSR role
            }
        }
        return user;
    }
    
    @Override
    public boolean isAuthenticated() {
        Principal p = context.getCallerPrincipal();
        if(p != null) {
            if(p.getName() != null && p.getName().length() > 0 && !p.getName().toUpperCase().equals("ANONYMOUS")) {
                return true;
            }
        }
        return false;
    }
    

}
