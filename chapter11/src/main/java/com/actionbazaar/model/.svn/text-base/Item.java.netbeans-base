/**
 *  Item.java
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
package com.actionbazaar.model;

import com.actionbazaar.account.Seller;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;

/**
 * Represents an item that is up for bidding.
 * An item has the following associations:
 * <ul>
 *     <li>It is associated with a seller.</li>
 *     <li>It is a member of 0 or more categories.</li>
 *     <li>It has 0 or more bid placed upon it.</li>
 * </ul>
 * It addition, an item:
 * <ul>
 *     <li>Has an initial price.</li>
 *     <li>A start and stop date for bidding</li>
 * </ul>
 */
@Entity
@Table(name = "ITEMS")
public class Item implements Serializable {

    /**
     * Unique identifier for the item
     */
    @Id
    @Column(name="ITEM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @TableGenerator(name = "ITEM_TABLE_GEN", table = "sequence",
            pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
            pkColumnValue = "ITEM_SEQ")
    private Long itemId;

    /**
     * Name of the item
     */

    @Column(name = "ITEM_NAME", nullable = false)
    private String itemName;

    /**
     * End date of the bid
     */
    @Column(name = "BID_END_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date bidEndDate;

    /**
     * Bid start date
     */
    @Column(name = "BID_START_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date bidStartDate;

    /**
     * Created date
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdDate;

    /**
     * Initial price
     */
    @Column(name = "INITIAL_PRICE")
    private BigDecimal initialPrice;

    /**
     * List of bids
     */
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Bid> bids;

    /**
     * Seller of this item
     */
    @ManyToOne
    @JoinColumn(name = "SELLER_ID", referencedColumnName = "USER_ID")
    private Seller seller;

    /**
     * An item can be associated with 0 or more categories.
     */
    @ManyToMany(mappedBy = "items")
    private Set<Category> category;

    /**
     * Creates an Item
     */
    public Item() {
        // empty constructor
    }

    /**
     * Creates a new item
     * @param itemId - item id
     */
    public Item(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * Creates a new item.
     * @param itemName - item name
     * @param bidEndDate - end date of bidding
     * @param bidStartDate - bid start date
     * @param createdDate - created date
     * @param initialPrice - initial price
     */
    public Item(String itemName, Date bidEndDate, Date bidStartDate, Date createdDate, BigDecimal initialPrice) {
        this.bidEndDate = bidEndDate;
        this.itemName = itemName;
        this.bidStartDate = bidStartDate;
        this.initialPrice = initialPrice;
        this.createdDate = createdDate;
    }

    /**
     * Returns an item name
     * @return item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Creates an item name
     * @param itemName - item name
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Returns the bid end date
     * @return bid end date
     */
    public Date getBidEndDate() {
        return bidEndDate;
    }

    /**
     * Sets the bid end date
     * @param bidEndDate - bid end date
     */
    public void setBidEndDate(Date bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

    /**
     * Returns a bid start date
     * @return bid start date
     */
    public Date getBidStartDate() {
        return bidStartDate;
    }

    /**
     * Sets the bid start date
     * @param bidStartDate - bid start date
     */
    public void setBidStartDate(Date bidStartDate) {
        this.bidStartDate = bidStartDate;
    }

    /**
     * Returns the created date
     * @return created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date
     * @param createdDate - created date
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Sets the initial price
     * @return initial price
     */
    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    /**
     * Sets the initial price
     * @param initialPrice - initial price
     */
    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    /**
     * Returns the item id
     * @return item id
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * Sets the item id
     * @param itemId - item id
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * Returns the bids
     * @return bids
     */
    public List<Bid> getBids() {
        return bids;
    }

    /**
     * Sets the bids
     * @param bids - bids
     */
    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    /**
     * Returns the bid
     * @param bid - bid
     * @return Bid
     */
    public Bid addBid(Bid bid) {
        getBids().add(bid);
        return bid;
    }

    /**
     * Removes the bid
     * @param bid - bid
     * @return Bid
     */
    public Bid removeBid(Bid bid) {
        getBids().remove(bid);
        return bid;
    }

    /**
     * Sets the seller
     * @param seller - seller
     */
    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    /**
     * Returns the seller
     * @return seller
     */
    public Seller getSeller() {
        return seller;
    }

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }

    public Category addCategory(Category category) {
        getCategory().add(category);
        return category;
    }

    public Category removeCategory(Category category){
        getCategory().remove(category);
        // category.setCategory(null);      // TODO FIX
        return category;
    }

    @Override
    public String toString() {
        return itemName;
    }
}
