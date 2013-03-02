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
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an item.
 */
@XmlRootElement(name="Bidder")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item implements Serializable {

    /**
     * Name of the item
     */
    @XmlElement
    private String itemName;

    /**
     * End date of the bid
     */
    @XmlElement
    private Date bidEndDate;

    /**
     * Bid start date
     */
    @XmlElement
    private Date bidStartDate;

    /**
     * Created date
     */
    @XmlElement
    private Date createdDate;

    /**
     * Initial price
     */
    @XmlElement
    private Double initialPrice;

    /**
     * Item id
     */
    @XmlElement
    private Long itemId;

    /**
     * List of bids
     */
    @XmlElement
    private List<Bid> bids;

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
    public Double getInitialPrice() {
        return initialPrice;
    }

    /**
     * Sets the initial price
     * @param initialPrice - initial price
     */
    public void setInitialPrice(Double initialPrice) {
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
}