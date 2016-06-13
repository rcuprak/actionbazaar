/**
 *  CreditCardManagerBean.java
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
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 * This bean uses container managed transactions 
 */
@Stateless(name = "CreditCardManager")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CreditCardManagerBean implements CreditCardManager {

    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("CreditCardManagerBean");
    
    /**
     * Charges a Credit Card
     * @param creditCard - credit card
     * @param amount - amount to be charged
     * @throws CreditProcessingException - exception
     */
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    @Override
    public void chargeCreditCard(CreditCard creditCard, BigDecimal amount)
            throws CreditProcessingException {
        // debit the credit card...
    }

    /**
     * Validates the credit card
     *
     * @param card - credit card
     * @return true if the credit card is valid
     */
    @Override
    public boolean validateCard(CreditCard card) throws CreditProcessingException{
        return true;
    }
}
