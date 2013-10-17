/**
 *  OrderManager.java
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

import com.actionbazaar.model.Order;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Handles persisting orders.
 * @author Ryan Cuprak
 */
@Stateless
public class OrderManager {
    
        /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("OrderManager");
    
    /**
     * Entity manager context
     */
    @PersistenceContext
    private EntityManager entityManager;
    
    /**
     * Persists the order
     * @param order - order to be saved 
     */
    public void createOrder(Order order) {
        entityManager.persist(order);
    }
    
}
