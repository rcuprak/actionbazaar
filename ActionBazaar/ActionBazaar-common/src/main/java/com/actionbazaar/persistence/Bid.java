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
package com.actionbazaar.persistence;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import com.actionbazaar.account.Bidder;

/**
 * Represents a bid on an item.
 */
@Entity
@XmlRootElement
public class Bid implements Serializable {

    /**
     * Date of the bid
     */
    private Date bidDate;
    /**
     * Unique identifier for the bid (db)
     */
    private Long bidId;
    /**
     * Bid price
     */
    private BigDecimal bidPrice;
    /**
     * Item for this bid
     */
    private Item item;
    /**
     * Bidder
     */
    private Bidder bidder;

    /**
     * Default constructor
     */
    public Bid() {
        // No content
    }

    /**
     * Constructs a new bid given the bidder/item/price
     * @param bidder - person doing the bidding
     * @param item - item being bid on
     * @param bidPrice - price
     */
    public Bid(Bidder bidder, Item item, BigDecimal bidPrice) {
        this.item = item;
        this.bidder = bidder;
        this.bidPrice = bidPrice;
    }

    /**
     * Returns the bid id
     * @return bid id
     */
    @Id
    @GeneratedValue
    public Long getBidId() {
        return bidId;
    }

    /**
     * Returns the bid date
     * @return bid date
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getBidDate() {
        return bidDate;
    }

    /**
     * Sets the bid date
     * @param bidDate - bid date
     */
    public void setBidDate(Date bidDate) {
        this.bidDate = bidDate;
    }

    /**
     * Sets the bid id
     * @param bidId - bid id
     */
    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    /**
     * Returns the bid price
     * @return bid price
     */
    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    /**
     * Sets the bid price
     * @param bidPrice - bid price
     */
    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    /**
     * Returns the item
     * @return item
     */
    @ManyToOne @JoinColumn
    public Item getItem() {
        return item;
    }

    /**
     * Sets the item being bid on
     * @param item - item
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Returns the bidder
     * @return bidder
     */
    @ManyToOne @JoinColumn
    public Bidder getBidder() {
        return bidder;
    }

    /**
     * Sets the bidder
     * @param bidder - bidder
     */
    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
    }
}
