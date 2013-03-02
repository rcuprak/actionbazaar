/**
 *  ShippingRequest.java
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
package com.actionbazaar.buslogic;

import com.actionbazaar.account.Address;
import com.actionbazaar.model.Item;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Represents a shipping request.
 */
public class ShippingRequest implements Serializable {
    
    /**
     * Item being shipped
     */
    private Item item;
    
    /**
     * Address the item is being shipped to
     */
    private Address address;
    
    /**
     * Amount of insurance on the item
     */
    private BigDecimal insuranceAmount;
    
    /**
     * Shipping Method
     */
    private ShippingMethod shippingMethod;

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
     * Returns the shipping address
     * @return shipping address
     */
    public Address getShippingAddress() {
        return address;
    }

    /**
     * Sets the shipping address
     * @param address - shipping address
     */
    public void setShippingAddress(Address address) {
        this.address = address;
    }

    /**
     * Returns the insurance amount
     * @return insurance amount
     */
    public BigDecimal getInsuranceAmount() {
        return insuranceAmount;
    }

    /**
     * Sets the insurance amount
     * @param insuranceAmount - insurance amount
     */
    public void setInsuranceAmount(BigDecimal insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    /**
     * Returns the shipping method
     * @return shipping method
     */
    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    /**
     * Sets the shipping method
     * @param shippingMethod - shipping method
     */
    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

}
