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
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import com.actionbazaar.persistence.Order;

/**
 * This represents a bidder within the ActionBazaar system.
 * @author Ryan Cuprak
 */
@Entity
@Table(name="BIDDERS")
@DiscriminatorValue(value="B")
@PrimaryKeyJoinColumn(name="USER_ID")
public class Bidder extends User implements Serializable {

    /**
     * Billing information
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<BillingInfo> billingInfo = new HashSet<BillingInfo>();

    /**
     * Orders
     */
    @OneToMany
    private Set<Order> orders;

    /**
     * Creates a bidder
     */
    public Bidder() {
        // Constructs a new bidder
    }

    /**
     * Creates a new bidder with the initial credit rating provided
     * @param username - username
     * @param password - password
     * @param firstName - first name
     * @param lastName - last name
     * @param billingInfo - billing information
     * @param address - address
     * @param dateCreated - date on which the account was verified
     * @param accountVerified - true if the account has been verified
     */
    public Bidder(String username, String password, String firstName, String lastName,
                  BillingInfo billingInfo,Address address, Date dateCreated, boolean accountVerified) {
        super(firstName,lastName,username, password,address,dateCreated,accountVerified);
        //this.billingInfo.add(billingInfo);
    }

    /**
     * Sets the billing info
     * @param billingInfo - billing info
     */
    public void setBillingInfo(Set<BillingInfo> billingInfo) {
        this.billingInfo = billingInfo;
    }


    /**
     * Returns the billing info
     * @return billing info
     */
    public Set<BillingInfo> getBillingInfo() {
        return billingInfo;
    }

    /**
     * Sets the list of order
     * @param orders - orders
     */
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    /**
     * Adds an order
     * @param order - order
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Returns the orders
     * @return orders
     */
    public Set<Order> getOrders() {
        return orders;
    }

}
