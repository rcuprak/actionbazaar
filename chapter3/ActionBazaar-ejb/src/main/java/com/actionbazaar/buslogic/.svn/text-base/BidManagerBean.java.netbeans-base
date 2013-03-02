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
package com.actionbazaar.buslogic;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jws.WebService;
import com.actionbazaar.persistence.Bid;
import com.actionbazaar.persistence.Item;

/**
 * Manages bids
 */
@Stateless(name = "BidManager")
@WebService
public class BidManagerBean implements BidManager, BidManagerRemote, BidManagerWS {

    /**
     * Sessions context
     */
    @Resource
    private SessionContext sc;

    /**
     * Initializes the BidManager
     */
    @PostConstruct
    public void initialize() {

    }

    /**
     * Adds a new bid
     * @param bid - bid
     * @return bid identifier
     */
    public Long addBid(Bid bid) {
      return null;
    }

    /**
     * Returns the bid identifier
     * @return bid identifier
     */
    private Long getBidId() {
        // Add Code for generating a unique key
        return Long.valueOf(1002);
    }

    /**
     * Cancels the bid
     * @param bid - bid
     */
    public void cancelBid(Bid bid) {
    }

    /**
     * Returns the bids
     * @param item - item
     * @return list<bid>
     */
    public List<Bid> getBids(Item item) {
        return item.getBids();
    }

    /**
     * Cleans-up
     */
    @PreDestroy
    public void cleanup() {

    }
    /**
     * Returns the bid
     * @param bidId - bid id
     * @return Bid
     */
    @Override
    public String getBid(long bidId) {
        Bid bid = new Bid();
        return "Hello World";
    }
}