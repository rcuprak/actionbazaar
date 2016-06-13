/**
 *  BidServiceRSImpl.java
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

/**
 * BidServiceRSImpl
 */
public class BidServiceRSImpl implements BidServiceRS {

    @Override
    public void addBid(BidDTO bid) {
        // adds a new bid
    }

    @Override
    public BidDTO getBid(long bidId) {
        return null;
    }

    @Override
    public void cancelBid(long bidId) {
        // cancels a bid
    }

    @Override
    public String listBids(String category, long userId, String startDate, String endDate) {
        return "";
    }
    
}
