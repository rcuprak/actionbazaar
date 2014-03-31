/**
 *  Group.java
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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A group.
 * @author Ryan Cuprak
 */
@Entity
@Table(name="groups")
public class Group implements Serializable {

    /**
     * User Id
     */
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    @Column(name="group_pk")
    private Long userId;

    /**
     * Reference back to the user
     */
    @ManyToOne @JoinColumn
    private User user;
    
    /**
     * User name
     */
    @Column(name="username")
    private String username;
    
    /**
     * Group identifier
     */
    @Column(name="groupid")
    private String groupId;

    /**
     * Default constructor for JPA
     */
    protected Group() {}

    /**
     * Creates a new group with the specified ID
     * @param groupId - group id
     */
    public Group(String groupId) {
        this.groupId = groupId;
    }

    /**
     * Sets the group ID
     * @param groupId - groupId
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

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
    protected void setUsername(String username) {
        this.username = username;
    }
}
