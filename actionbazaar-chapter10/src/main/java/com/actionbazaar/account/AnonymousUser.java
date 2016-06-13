/**
 *  BillingInfo.java
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
package com.actionbazaar.account;

import java.io.Serializable;

/**
 * Place-holder for a user that hasn't logged in yet. 
 * @author Ryan Cuprak
 */
public class AnonymousUser extends User implements Serializable {
    
   
    /**
     * Default constructor
     */
    public AnonymousUser() {
        setUsername("Anonymous");
    }
    
    /**
     * Return true - we are anyonmous
     * @return true
     */
    @Override
    public boolean isAnonymous() {
        return true;
    }

}
