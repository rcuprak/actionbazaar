/**
 *  EJB 3 in Action
 *  Book: http://www.manning.com/panda/
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
package com.actionbazaar.persistence;

import java.io.Serializable;

/**
 * Login info object
 */
public class LoginInfo implements Serializable {

    /**
     * User name
     */
    protected String username;

    /**
     * Password
     */
    protected String password;

    /**
     * Returns the username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user name
     * @param userName - user name
     */
    public void setUsername(String userName) {
        this.username = userName;
    }

    /**
     * Sets the password
     * @param password - password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}