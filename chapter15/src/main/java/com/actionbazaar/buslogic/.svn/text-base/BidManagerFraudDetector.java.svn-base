/**
 *  BidManagerFraudDetector.java
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

import com.actionbazaar.BidManagerQualifier;
import com.actionbazaar.model.Bid;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

/**
 * Checks for fraud on a bid
 * @author Ryan Cuprak
 */
@Decorator
public abstract class BidManagerFraudDetector implements BidManager {
    
    /**
     * BidManager we are wrapping
     */
    @Inject @Delegate @BidManagerQualifier
    private BidManager bidManager;
    
    /**
     * Checks for fraud on a bid
     * @param bid - bid
     */
    @Override
    public void placeBid(Bid bid) {
        Logger.getLogger("Decorator").log(Level.INFO, "Checking for fraud...");
        bidManager.placeBid(bid);
        Logger.getLogger("Decorator").log(Level.INFO, "Fraud check complete...");
    }
}
