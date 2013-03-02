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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 * Activation link - tracks activation codes.
 * @author Ryan Cuprak
 */
@Entity
public class ActivationLink implements Serializable {

    /**
     * Primary key
     */
    @Id @GeneratedValue(strategy= GenerationType.TABLE)
    private Long activationPk;

    /**
     * Random UID - used in the URL that the user must click on
     */
    @NotNull
    private String randomUID;

    /**
     * Reference to the user
     */
    @ManyToOne @JoinColumn
    private User user;

    /**
     * Action to be performed when the link is clicked
     */
    @NotNull
    private String action;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expiration;

    /**
     * Default constructor
     */
    public ActivationLink() {
        // default constructor for JPA
    }


    /**
     * Creates a new ActivationLink
     * @param randomUID - random UID
     * @param action - action
     * @param expiration - expiration date
     */
    public ActivationLink(String randomUID, String action, Date expiration) {
        this.randomUID = randomUID;
        this.action = action;
        this.expiration = expiration;
    }

    /**
     * Returns the activation PK
     * @return activation PK
     */
    public Long getActivationPk() {
        return activationPk;
    }

    /**
     * Sets the activation PK
     * @param activationPk - activation PK
     */
    public void setActivationPk(Long activationPk) {
        this.activationPk = activationPk;
    }

    /**
     * Returns the random UID
     * @return random UID
     */
    public String getRandomUID() {
        return randomUID;
    }

    /**
     * Sets the random UID
     * @param randomUID - random UID
     */
    public void setRandomUID(String randomUID) {
        this.randomUID = randomUID;
    }

    /**
     * Returns the user
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user
     * @param user - user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns the action
     * @return action
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the action
     * @param action - action
     */
    public void setAction(String action) {
        this.action = action;
    }
}
