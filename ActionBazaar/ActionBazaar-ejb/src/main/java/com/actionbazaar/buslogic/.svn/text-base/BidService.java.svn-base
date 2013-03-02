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
package com.actionbazaar.buslogic;

import java.util.List;
import javax.ejb.Local;
import com.actionbazaar.account.Bidder;
import com.actionbazaar.credit.CreditCardSystemException;
import com.actionbazaar.credit.CreditProcessingException;
import com.actionbazaar.persistence.Bid;
import com.actionbazaar.persistence.CreditCard;
import com.actionbazaar.persistence.Item;

/**
 * Manages a bid
 * @author Ryan Cuprak
 */
@Local
public interface BidService {

    /**
        * Adds a bid
        * @param bid - bid
        */
       void addBid(Bid bid);

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

       /**
        * Places a snag-it order
        * @param item - item being ordered
        * @param bidder - bidder
        * @param card - credit card
        */
       public void placeSnagItOrder(Item item, Bidder bidder, CreditCard card) throws CreditProcessingException, CreditCardSystemException;

}
