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

import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a bidder
 */
@XmlRootElement(name="Bidder")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bidder extends User {

    /**
     * credit rating
     */
    @XmlElement
    private Long creditRating;

    /**
     * Bids
     */
    @XmlElement
    private Set<Bid> bids;

    /**
     * Creates a bidder
     */
    public Bidder() {
        // Constructs a new bidder
    }

    /**
     * Creates a new bidder object
     * @param creditRating - credit rating
     */
    public Bidder(Long creditRating) {
        this.creditRating = creditRating;
    }

    /**
     * Creates a new credit rating
     * @return credit rating
     */
    public Long getCreditRating() {
        return creditRating;
    }

    /**
     * Sets the credit rating
     * @param creditRating - credit rating
     */
    public void setCreditRating(Long creditRating) {
        this.creditRating = creditRating;
    }

    /**
     * Returns the bids
     * @return bids
     */
    public Set<Bid> getBids() {
        return bids;
    }

    /**
     * Sets the bids
     * @param bids - bids
     */
    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }
}