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
package com.actionbazaar.buslogic;

import javax.ejb.Remote;
import com.actionbazaar.persistence.BillingInfo;
import com.actionbazaar.persistence.BiographicalInfo;
import com.actionbazaar.persistence.LoginInfo;

/**
 * Creates a BidderAccountCreator
 */
@Remote
public interface BidderAccountCreator {

    /**
     * Adds a new log info to the system
     * @param loginInfo - login info
     */
    void addLoginInfo(LoginInfo loginInfo);

    /**
     * Returns a biographical info
     * @param biographicalInfo - biographical info
     * @throws WorkflowOrderViolationException - thrown if there is an error
     */
    void addBiographicalInfo(BiographicalInfo biographicalInfo);

    /**
     * Adds a billing info
     * @param billingInfo - billing info
     * @throws WorkflowOrderViolationException
     */
    void addBillingInfo(BillingInfo billingInfo);

    /**
     * Cancel account creation
     */
    void cancelAccountCreation();

    /**
     * Creates a account
     */
    void createAccount();
}