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
package com.actionbazaar.persistence;

import java.io.Serializable;

/**
 * Billing info
 */
public class BillingInfo implements Serializable {

    /**
     * Account number
     */
    protected String accountNumber;

    /**
     * Credit card type
     */
    protected String creditCardType;

    /**
     * Expiration date
     */
    protected String expiryDate;

    /**
     * Creates a new BillingInfo object
     */
    public BillingInfo() {
        // Billing info
    }

    /**
     * Returns the account number
     * @return account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Returns the expiration date
     * @return expiration date
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Returns the credit card type
     * @return credit card type
     */
    public String getCreditCardType() {
        return creditCardType;
    }

    /**
     * Sets the account number
     * @param accountNumber - account number
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Sets the expiration date
     * @param expiryDate - expiration date
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Sets the credit card type
     * @param creditCardType - credit card type
     */
    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }
}
