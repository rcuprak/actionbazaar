/**
 *  BidDTO.java
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
package com.actionbazaar.rs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * BidDTO - data transfer object to be exposed via REST.
 * Why didn't we just expose Bid? Serializing bid would result in transferring
 * the entire object graph back to the client. Also, there is a mismatch between
 * the representation of our data model in Java and the representation that
 * is needed to effectively exchange via REST.
 */
@XmlRootElement(name="Bid")							
@XmlAccessorType(XmlAccessType.FIELD)					
public class BidDTO {
    
    /**
     * Bid Date
     */
    @XmlElement								
    private XMLGregorianCalendar bidDate;					

    /**
     * Bid ID
     */
    @XmlAttribute								
    private Long bidId;

    /**
     * Bid price
     */
    @XmlElement
    private double bidPrice;

    /**
     * Item id
     */
    @XmlElement
    private long itemId;

    /**
     * Bidder id
     */
    @XmlElement
    private long bidderId;
    
    /**
     * Default constructor
     */
    public BidDTO() {
        // default constructor
    }

    /**
     * Returns the bid date
     * @return bid date
     */
    public XMLGregorianCalendar getBidDate() {
        return bidDate;
    }

    /**
     * Sets the bid date
     * @param bidDate - bid date
     */
    public void setBidDate(XMLGregorianCalendar bidDate) {
        this.bidDate = bidDate;
    }

    /**
     * Returns the bid id
     * @return bid id
     */
    public Long getBidId() {
        return bidId;
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
    public double getBidPrice() {
        return bidPrice;
    }

    /**
     * Sets the bid price
     * @param bidPrice - bid price
     */
    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    /**
     * Returns the item id
     * @return item id
     */
    public long getItemId() {
        return itemId;
    }

    /**
     * Sets the item id
     * @param itemId - item id
     */
    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    /**
     * Returns the bidder id
     * @return bidder id
     */
    public long getBidderId() {
        return bidderId;
    }

    /**
     * Sets the bidder id
     * @param bidderId - bidder id
     */
    public void setBidderId(long bidderId) {
        this.bidderId = bidderId;
    }
}

