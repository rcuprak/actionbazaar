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
package com.actionbazaar.account;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Represents an ActionBazaar employee
 * @author Ryan Cuprak
 */
@Entity
@Table(name="employees")
@DiscriminatorValue(value="E")
@PrimaryKeyJoinColumn(name="USER_ID")
public class Employee extends User implements Serializable {

    /**
     * Employee title
     */
    private String title;
    
    /**
     * Default constructor for JPA
     */
    public Employee() {}

    /**
     * Constructs a new employee
     * @param firstName - first name
     * @param lastName - last name
     * @param username - username
     * @param password - password
     * @param dateCreated - date created
     * @param title - employee title
     */
    public Employee(String firstName, String lastName, String username, String password, Date dateCreated, String title) {
        super(firstName,lastName,username,password,null,dateCreated,true);   
        this.title = title;
    }

    /**
     * Returns the employee's title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the employee's title
     * @param title - title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
