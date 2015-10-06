/**
 *  NewsletterBean.java
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

import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Sends out a newsletter
 */
@Stateless
public class NewsletterBean {

    /**
     * Entity Manager
     */
    @PersistenceContext(unitName="actionbazaar")
    private EntityManager em;
    
    /**
     * Sends out the monthly newsletter
     */
    @Schedule(second = "0", minute = "0", hour = "0", dayOfMonth = "1",
    month = "*", year = "*")
    public void sendMonthlyNewsletter() {
        // sends out the newsleter
    }

    /**
     * Sends out the holiday newsletter
     */
    @Schedules({
        @Schedule(second = "0", minute = "0", hour = "12",
        dayOfMonth = "Last Thu", month = "Nov", year = "*"),
        @Schedule(second = "0", minute = "0", hour = "12",
        dayOfMonth = "18", month = "Dec", year = "*")
    })
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void sendHolidayNewsletter() {
        // Sends out a holiday newsletter...
    }
}
