/**
 *  CreditCard.java
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
package com.actionbazaar.model;

import com.actionbazaar.account.BillingInfo;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Encapsulates information about a credit card.
 * @author Ryan Cuprak
 */
@Entity
@DiscriminatorValue(value="C")
@PrimaryKeyJoinColumn(name="BILLING_ID")
public class CreditCard extends BillingInfo implements Serializable {

    /**
     * Credit card identifier
     */
    @Id @GeneratedValue
    private Long creditCardId;

    /**
     * Name appearing on the card
     */
    @Size(min=3,max=45,message="{step3_nameOnCardRequired}")
    private String nameOnCard;

    /**
     * Account number
     */
    @Size(min=3,max=45,message="{step3_accountNumberRequired}")
    private String accountNumber;

    /**
     * Expiration month on the card
     */
    @Size(min=2,max=2,message="{step3_twoDigitExpirationMonth}")
    @NotNull
    @DecimalMin(value="1", message="{step3_minimumMonthValue}")
    @DecimalMax(value="12", message="{step3_maximumMonthValue}")
    @Digits(integer = 2, fraction = 0,message="{step3_invalidExpirationMonth}")
    private String expirationMonth;

    /**
     * Expiration year on the card
     */
    @Size(min=4,max=4,message="{step3_twoDigitExpirationYear}")
    @NotNull
    @DecimalMin(value="2012",message="{step3_minimumYearValue}")
    @Digits(integer = 4, fraction = 0,message = "{step3_invalidExpirationYear}")
    private String expirationYear;

    /**
     * Security code appear on the back of the card
     */
    @Size(min=3,max=4,message="{step3_securityCode}")
    @Digits(integer = 4, fraction = 0,message="{step3_securityCode}")
    private String securityCode;

    /**
     * Type of the credit card
     */
    private CreditCardType creditCardType;

    /**
     * Sets the credit card id
     * @param creditCardId - credit card id
     */
    public void setCreditCardId(Long creditCardId) {
        this.creditCardId = creditCardId;
    }

    /**
     * Returns credit card id
     * @return credit card id
     */
    public Long getCreditCardId() {
        return creditCardId;
    }

    /**
     * Returns the name that appears on the car
     * @return name on card
     */
    public String getNameOnCard() {
        return nameOnCard;
    }

    /**
     * Sets the name on the card
     * @param nameOnCard - name on the card
     */
    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    /**
     * Returns the expiration month on the card
     * @return expiration month on card
     */
    public String getExpirationMonth() {
        return expirationMonth;
    }

    /**
     * Sets the expiration month
     * @param expirationMonth - expiration month
     */
    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    /**
     * Returns the expiration year
     * @return expiration year
     */
    public String getExpirationYear() {
        return expirationYear;
    }

    /**
     * Sets the expiration year
     * @param expirationYear - expiration year
     */
    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    /**
     * Returns the security code
     * @return security code
     */
    public String getSecurityCode() {
        return securityCode;
    }

    /**
     * Sets the security code
     * @param securityCode - security code
     */
    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    /**
     * Returns the credit card type
     * @return credit card type
     */
    public CreditCardType getCreditCardType() {
        return creditCardType;
    }

    /**
     * Sets the credit card type
     * @param creditCardType - credit card type
     */
    public void setCreditCardType(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }

    /**
     * Sets the card number
     * @param accountNumber - card number
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Returns the card number
     * @return card number
     */
    public String getAccountNumber() {
        return accountNumber;
    }
}

