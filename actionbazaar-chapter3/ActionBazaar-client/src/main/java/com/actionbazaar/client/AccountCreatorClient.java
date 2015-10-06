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
package com.actionbazaar.client;

import com.actionbazaar.buslogic.BidderAccountCreator;
import com.actionbazaar.persistence.BillingInfo;
import com.actionbazaar.persistence.BiographicalInfo;
import com.actionbazaar.persistence.LoginInfo;
import javax.ejb.EJB;
import javax.swing.JFrame;

/**
 * AccountCreatorClient
 */
public class AccountCreatorClient extends JFrame {

    /**
     * Account creator
     */
    @EJB
    private static BidderAccountCreator accountCreator;

    /**
     * Creates a new AccountCreatorClient
     */
    public AccountCreatorClient() {
        super("Accont Creator Client");
    }

    public static void main(String[] args) {

        LoginInfo login = new LoginInfo();
        login.setUsername("dpanda");
        login.setPassword("welcome");

        accountCreator.addLoginInfo(login);

        BiographicalInfo bio = new BiographicalInfo();
        bio.setFirstName("Debu");
        bio.setLastName("Panda");

        accountCreator.addBiographicalInfo(bio);

        BillingInfo billing = new BillingInfo();
        billing.setCreditCardType("VISA");
        billing.setAccountNumber("0123456789");

        accountCreator.addBillingInfo(billing);

        // Create account
        accountCreator.createAccount();
    }
}