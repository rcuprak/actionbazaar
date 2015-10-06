/**
 *  BazaarAccount.java
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

import com.actionbazaar.model.Item;
import com.actionbazaar.model.Order;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * An account that can sell and/or bid on items
 * @author Ryan Cuprak
 */
@Entity
@DiscriminatorValue(value = "A")
public class BazaarAccount extends User implements Serializable {
    
    /**
     * Serial UID
     */
    private static final long serialVersionUID = 559161073546347543L;
    
    /**
     * Billing information
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<BillingInfo> billingInfo = new HashSet<>();

    /**
     * Orders
     */
    @OneToMany
    private Set<Order> orders;

       /**
     * Commission rate
     */
    @Column(name = "COMM_RATE")
    private BigDecimal commissionRate;

    /**
     * Maximum number of items that can be sold
     */
    @Column(name = "MAX_ITEMS")
    private Long maxItemsAllowed;

    /**
     * Items this seller is selling
     */
    @OneToMany(mappedBy = "seller")
    private Set<Item> itemsSelling;

    /**
     * Default constructor for JPA/JSF
     */
    public BazaarAccount() {}
    
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
    public BazaarAccount(String firstName, String lastName, String username, String password, Address address, Date dateCreated, boolean accountVerified) {
        super(firstName,lastName,username,password,address,dateCreated,accountVerified);
    }

    /**
     * Returns the commission rate
     * @return commission rate
     */
    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    /**
     * Sets the commission rate
     * @param commissionRate - commission rate
     */
    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    /**
     * Maximum number of items
     * @return maximum items
     */
    public Long getMaxItemsAllowed() {
        return maxItemsAllowed;
    }

    /**
     * Sets the maximum items allowed
     * @param maxItemsAllowed - max items allowed
     */
    public void setMaxItemsAllowed(Long maxItemsAllowed) {
        this.maxItemsAllowed = maxItemsAllowed;
    }
    
    /**
     * Returns the items that we are selling
     * @return items being sold
     */
    public Set<Item> getItemsSelling() {
        return itemsSelling;
    }

    /**
     * Sets the items that we are selling
     * @param itemsSelling - selling
     */
    public void setItemsSelling(Set<Item> itemsSelling) {
        this.itemsSelling = itemsSelling;
    }

    /**
     * Adds an new for the seller
     * @param item - item being sold
     * @return Item
     */
    public Item addItem(Item item) {
        getItemsSelling().add(item);
        item.setSeller(this);
        return item;
    }


    public Item removeItem(Item item) {
        getItemsSelling().remove(item);
        item.setSeller(null);
        return item;
    }
    
    /**
     * Sets the billing information
     * @param billingInfo - billing information
     */
    public void addBillingInfo(BillingInfo billingInfo) {
        this.billingInfo.add(billingInfo);
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
