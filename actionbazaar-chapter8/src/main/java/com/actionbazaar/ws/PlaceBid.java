/**
 * PlaceBid.java EJB 3 in Action Book: http://manning.com/panda2/ Code:
 * http://code.google.com/p/action-bazaar/ Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.actionbazaar.ws;

import com.actionbazaar.model.Bid;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * PlaceBid implementation.
 */
@Stateless
@WebService(endpointInterface = "com.actionbazaar.ws.PlaceBidWS")
public class PlaceBid implements PlaceBidWS {

    /**
     * Submits a bid
     * @param userId - user id
     * @param itemId - item id
     * @param bidPrice - bid price
     * @return identifier of the bid request
     */
    @Override
    public long submitBid(long userId, long itemId, double bidPrice) {
        return 0;

    }

    /**
     * Returns outstanding bid requests
     * @return bids
     */
    public List<Bid> getBids() {
        return null;
    }
}
