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

import com.actionbazaar.persistence.Bid;
import com.actionbazaar.persistence.Item;
import java.util.List;
import javax.ejb.Remote;

/**
 * Remote interface for BidManager
 */
public interface BidManager {

    /**
     * Adds a bid
     * @param bid - bid
     * @return identifier for the bid
     */
    Long addBid(Bid bid);

    /**
     * Cancels the bid
     * @param bid - bid
     */
    void cancelBid(Bid bid);

    /**
     * Returns a list of the bids
     * @param item - item
     * @return list of bids
     */
    List<Bid> getBids(Item item);

    /**
     * Returns a bid provided the
     * @param bidId 
     * @return Bid
     */
    String getBid(long bidId);

}
