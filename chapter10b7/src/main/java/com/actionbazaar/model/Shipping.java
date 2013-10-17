/**
 *  Shipping.java
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

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Shipping information
 */
@Entity
public class Shipping implements Serializable {

    /**
     * Shipping cost
     */
    private BigDecimal cost;

    /**
     * Shipping id
     */
    private Long shippingId;
    
    /**
     * Default constructor
     */
    protected Shipping() {
        cost = new BigDecimal("0");
    }
    
    /**
     * Constructs a new shipping information
     * @param cost - cost of the shippment
     */
    public Shipping(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * Returns the shipping Id
     * @return shipping id
     */
    @Id
    @GeneratedValue
    public Long getShippingId() {
        return shippingId;
    }

    /**
     * Sets the shipping id
     * @param shippingId - shipping id
     */
    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    /**
     * Cost of the shipping
     * @param cost - cost
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * Returns the cost of the shipping
     * @return shipping cost
     */
    public BigDecimal getCost() {
        return cost;
    }


}
