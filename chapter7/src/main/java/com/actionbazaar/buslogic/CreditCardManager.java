/**
 *  CreditCardManager.java
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

import com.actionbazaar.buslogic.exceptions.CreditProcessingException;
import com.actionbazaar.model.CreditCard;
import java.math.BigDecimal;

/**
 * Credit Card Manager
 */
public interface CreditCardManager {
       
    /**
     * Charges a Credit Card
     * @param creditCard - credit card
     * @param amount - amount to be charged
     * @throws CreditProcessingException - exception
     */
    public void chargeCreditCard(CreditCard creditCard, BigDecimal amount) throws CreditProcessingException;

    /**
     * Validates the credit card
     *
     * @param card - credit card
     * @return true if the credit card is valid
     */
    public boolean validateCard(CreditCard card) throws CreditProcessingException;
}
