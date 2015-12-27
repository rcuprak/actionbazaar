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

import com.actionbazaar.buslogic.BidManagerRemote;
import com.actionbazaar.buslogic.BidderAccountCreator;
import com.actionbazaar.persistence.BillingInfo;
import com.actionbazaar.persistence.BiographicalInfo;
import com.actionbazaar.persistence.LoginInfo;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * AccountCreatorClient
 */
public class StandAloneAccountCreatorClient {

    public static void main(String[] args) {

        StandAloneAccountCreatorClient client = new StandAloneAccountCreatorClient();
        client.initialize();
        client.createAccount();
        client.verifyAccountCreation();
    }

    public void initialize() {
        try {
            Context ctxt = new InitialContext();
            accountCreator = (BidderAccountCreator) ctxt
                    .lookup("java:global/actionbazaar-chapter3-ear/actionbazaar-chapter3-ejb/BidderAccountCreator");
            bidManager = (BidManagerRemote) ctxt
                    .lookup("java:global/actionbazaar-chapter3-ear/actionbazaar-chapter3-ejb/BidManager");
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void createAccount() {
        LoginInfo login = new LoginInfo();
        login.setUsername("kpanda");
        login.setPassword("welcome");

        accountCreator.addLoginInfo(login);

        BiographicalInfo bio = new BiographicalInfo();
        bio.setFirstName("KungFu");
        bio.setLastName("Panda");

        accountCreator.addBiographicalInfo(bio);

        BillingInfo billing = new BillingInfo();
        billing.setCreditCardType("VISA");
        billing.setAccountNumber("9876543210");

        accountCreator.addBillingInfo(billing);

        // Create account
        accountCreator.createAccount();

    }

    public void verifyAccountCreation() {
        // verify that the account got created.
        List<List<String>> bidders = bidManager.getBidders();
        bidders.forEach(System.out::println);
    }

    /**
     * Account creator
     */
    private BidderAccountCreator accountCreator;

    private BidManagerRemote bidManager;
}