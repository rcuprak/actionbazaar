/**
 *  BidManager.java
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

import com.actionbazaar.account.BazaarAccount;
import com.actionbazaar.buslogic.exceptions.CreditCardSystemException;
import com.actionbazaar.model.Bid;
import com.actionbazaar.model.CreditCard;
import com.actionbazaar.model.Item;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.security.PermitAll;

/**
 * Bid Manager
 * @author Ryan Cuprak
 */
public interface BidManager {
    
     /**
     * Places a snag-it order
     * @param item - item to be purchased
     * @param bidder - bidder purchasing the item
     * @param card - card to be debited 
     */
    void placeSnagItOrder(Item item, BazaarAccount bazaarAccount, CreditCard card);
    
    /**
     * Closes a bid
     * @param item - item being purchased
     * @param bidder - bidder
     * @param initialPrice - initial price
     */
    void closeBid(Item item, BazaarAccount bazaarAccount, BigDecimal initialPrice ) throws CreditCardSystemException;
    
 
    /**
     * Cancels a bid
     * @param bid - bid to be canceled
     */
    void cancelBid(Bid bid);

    /**
     * Returns the bids
     * @param item - item
     * @return bids
     */
    @PermitAll
    List<Bid> getBids(Item item);
    
    /**
     * Must be authenticated as a bidder to bid!
     * @param bid - bidder
     */
    void placeBid(Bid bid);
}
