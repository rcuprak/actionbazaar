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
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import com.actionbazaar.persistence.Item;

/**
 * Represents a seller within ActionBazaar
 */
@Entity
@DiscriminatorValue(value = "S")
@XmlRootElement
public class Seller extends User implements Serializable {

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
    private Set<Item> items;

    /**
     * Default constructor for JPA
     */
    public Seller() {
        // implementation empty
    }

    /**
     * Creates a new seller
     * @param firstName - seller's first name
     * @param lastName - seller's last name
     * @param username - user name
     * @param password - password
     * @param address - address of the seller
     * @param dateCreated - date on which the account was created
     * @param accountVerified - true if the account has been verified
     */
    public Seller(String firstName, String lastName, String username, String password, Address address, Date dateCreated, boolean accountVerified,BigDecimal commisionRate) {
        super(firstName,lastName,username,password,address,dateCreated,accountVerified);
        this.commissionRate = commisionRate;
    }




    public BigDecimal getCommissionRate() {
        return commissionRate;
    }


    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }



    public Long getMaxItemsAllowed() {
        return maxItemsAllowed;
    }


    public void setMaxItemsAllowed(Long maxItemsAllowed) {
        this.maxItemsAllowed = maxItemsAllowed;
    }



    public Set<Item> getItems() {
        return items;
    }


    public void setItems(Set<Item> items) {
        this.items = items;
    }

    /**
     * Adds an new for the seller
     * @param item - item being sold
     * @return Item
     */
    public Item addItem(Item item) {
        getItems().add(item);
        item.setSeller(this);
        return item;
    }


    public Item removeItem(Item item) {
        getItems().remove(item);
        item.setSeller(null);
        return item;
    }
}
