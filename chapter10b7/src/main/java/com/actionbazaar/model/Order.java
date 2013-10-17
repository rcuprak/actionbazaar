/**
 *  Order.java
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

import com.actionbazaar.account.BazaarAccount;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Represents an Order
 */
@Entity
@Table(name="orders")
public class Order implements Serializable {

    /**
     * Order id
     */
    @Id @GeneratedValue
    private Long orderId;

    /**
     * Person that bid
     */
    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn
    private BazaarAccount bidder;

    /**
     * Item on that was bid on
     */
    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn
    private Item item;

    /**
     * Shipping address
     */
    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn
    private Shipping shipping;

    /**
     * Credit card
     */
    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn
    private CreditCard creditCard;
    
    /**
     * Bid to which this order is attached to
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Bid bid;
    
    /**
     * Order status
     */
    @Enumerated
    private OrderStatus orderStatus;

    /**
     * Default constructor for JPA
     */
    public Order() {
        // Default constructor
    }

    /**
     * Creates a new order
     * @param item  - item in the order
     * @param bidder - bidder
     * @param creditCard - credit card
     * @param shipping - shipping information
     */
    public Order(Item item, Bid bid, BazaarAccount bidder, CreditCard creditCard, Shipping shipping) {
        this.item = item;
        this.bidder = bidder;
        this.creditCard = creditCard;
        this.bid = bid;
        this.shipping = shipping;
    }

    /**
     * Sets the orderId
     * @param orderId - order ID
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * Returns the order id
     * @return order id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * Returns the bidder
     * @return bidder
     */
    public BazaarAccount getBidder() {
        return bidder;
    }

    /**
     * Sets the bidder
     * @param bidder - bidder
     */
    public void setBidder(BazaarAccount bidder) {
        this.bidder = bidder;
    }

    /**
     * Returns the item
     * @return item
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets the item
     * @param item - item
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Return the shipping information
     * @return shipping
     */
    public Shipping getShipping() {
        return shipping;
    }

    /**
     * Sets the credit card
     * @param creditCard - credit card
     */
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    /**
     * Returns the credit card
     * @return credit card
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * Sets the shipping information
     * @param shipping - shipping information
     */
    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    /**
     * Sets the order status
     * @param orderStatus - order status
     */
    public void setStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Returns the order status
     * @return order status
     */
    public OrderStatus getStatus() {
        return orderStatus;
    }
    
    /**
     * Sets the bid this order is associated with
     * @param bid - bid we are associated with
     */
    public void setBid(Bid bid) {
        this.bid = bid;
    }
    
    /**
     * Returns the associated bid
     * @return bid
     */
    public Bid getBid() {
        return bid;
    }
}
