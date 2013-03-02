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

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import com.actionbazaar.credit.CreditCardSystemException;
import com.actionbazaar.credit.CreditProcessingException;
import com.actionbazaar.persistence.CreditCard;

/**
 * Handles processing of credit card transactions.
 * @author Ryan Cuprak
 */
@Stateless(name="CreditCardManager")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CreditCardManagerBean implements CreditCardManager {

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void chargeCreditCard(CreditCard creditCard, BigDecimal amount) throws CreditProcessingException {
        // debit the credit card...
    }

    @Override
    public void validateCard(CreditCard creditCard) throws CreditCardSystemException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
