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
import java.util.Date;

/**
 */
public class User implements Serializable {

    /**
     * first name
     */
    private String firstName;

    /**
     * last name
     */
    private String lastName;

    /**
     * user id
     */
    private String userId;

    /**
     * User's picture
     */
    private byte[] picture;

    /**
     * Birthday
     */
    private Date birthDate;


    public User() {
    }

    /**
     * Constructs a new user given basic information
     * @param firstName - first name
     * @param lastName - last name
     * @param userId - user id
     */
    public User(String firstName, String lastName, String userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }

    /**
     * Returns first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name
     * @param firstName - first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName.toUpperCase();
    }

    /**
     * Returns user's last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name
     * @param lastName - last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the user id
     * @return user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Returns the user id
     * @param userId - user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Returns the birthday
     * @return birthday
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the birthday
     * @param birthDate - birth date
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
