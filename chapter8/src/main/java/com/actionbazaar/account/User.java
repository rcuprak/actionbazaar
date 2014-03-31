/**
 *  User.java
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

import org.apache.commons.codec.binary.Base64;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author rcuprak
 */
/**
 * Base class defining the basic attributes of a user.
 * @see com.actionbazaar.account.User
 * @see com.actionbazaar.account.Seller
 */
@Entity
@Table(name="USERS")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="USER_TYPE",discriminatorType = DiscriminatorType.STRING,length = 1)
public abstract class User implements Serializable {

    /**
     * User logger
     */
    private static final Logger logger = Logger.getLogger("User");

    /**
     * User Id
     */
    @Id @GeneratedValue(strategy= GenerationType.TABLE)
    @Column(name="USER_ID")
    private Long userId;

    /**
     * first name
     */
    private String firstName;

    /**
     * last name
     */
    private String lastName;

    /**
     * User's picture
     */
    private byte[] picture;

    /**
     * Birthday
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthDate;

    /**
     * Password
     */
    private String password;

    /**
     * Username
     */
    private String username;

    /**
     * Address information
     */
    @Embedded
    private Address address;

    /**
     * Email address
     */
    private String email;

    /**
     * True if this entity represents a guest
     */
    private boolean guest;

    /**
     * Date that the user was created
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created;

    /**
     * Flag indicated that the account has been verified - user clicked on a link they received via email.
     */
    private boolean accountVerified;

    /**
     * Groups
     */
    @OneToMany(cascade = CascadeType.ALL,fetch= FetchType.LAZY)
    private Set<Group> groups = new HashSet<Group>();

    /**
     * Default constructor
     */
    public User() {
        address = new Address();
    }

    /**
     * Constructs a new user given basic information
     * @param firstName - first name
     * @param lastName - last name
     * @param username - username
     * @param password - password
     * @param address - address information
     * @param dateCreated - date on which the account was created
     * @param accountVerified  - flag indicating
     */
    public User(String firstName, String lastName, String username, String password, Address address, Date dateCreated, boolean accountVerified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        if(password != null) {
            createPassword(password);
        }
        this.address = address;
        this.created = dateCreated;
        this.accountVerified = accountVerified;
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
    protected void setLastName(String lastName) {
        this.lastName = lastName;
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
    
    /**
     * Creates a password - encoding it.
     * @param password - raw password
     */
    public final void createPassword(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            String encodedPassword = Base64.encodeBase64String(digest);
            this.password = encodedPassword;
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE,"Password creation failed",e);
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            logger.log(Level.SEVERE, "Password creation failed", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the password
     * @param password - password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the email address
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address
     * @param email - email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the date on which the account was created
     * @return date created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Sets the date that the account was created
     * @param created - date created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * Returns true if the account has been verified
     * @return true if account has been verified
     */
    public boolean isAccountVerified() {
        return accountVerified;
    }

    /**
     * Sets the account verified flag
     * @param accountVerified - true if the account has been verified
     */
    public void setAccountVerified(boolean accountVerified) {
        this.accountVerified = accountVerified;
    }

    /**
     * Adds a new group
     * @param groupId - group id
     */
    public void addGroup(String groupId) {
        Group grp = new Group(groupId);
        grp.setUsername(username);
        groups.add(grp);
        
    }

    /**
     * Returns the current set of groups for this user
     * @return groups
     */
    public Set<Group> getGroups() {
        return groups;
    }

    /**
     * Sets the guest flag
     * @param guest - guest flag
     */
    public void setGuest(boolean guest) {
        this.guest = guest;
    }

    /**
     * Returns true if the user is a guest
     * @return true if guest
     */
    public boolean isGuest() {
        return guest;
    }

    /**
     * Returns the display presentation
     * @return display presentation
     */
    public String getDisplay() {
        if(guest) {
            return "Guest";
        }
        return firstName;
    }
}
