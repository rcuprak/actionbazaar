/**
 *  DevelopmentStartup.java
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
package com.actionbazaar.setup;

import com.actionbazaar.account.BazaarAccount;
import com.actionbazaar.account.Employee;
import com.actionbazaar.account.UserService;
import com.actionbazaar.buslogic.ItemManager;
import com.actionbazaar.model.Category;
import com.actionbazaar.model.Item;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

/**
 * Initializes the system at start-up for developer testing.
 * @author Ryan Cuprak
 */
@Alternative
public class DevelopmentStartup extends SystemStartup {
    
    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("DevelopmentStartup");
    
    /**
     * User service
     */
    @Inject
    private UserService userService;
    
    /**
     * Item manager
     */
    @Inject
    private ItemManager itemManager;
  
    /**
     * Initializes the system
     */
    @Override
    public void init() {
        logger.info("Development startup initialized...");
        
        // Add a default user into the system
        if(userService.getUser("admin") == null) {
            Employee employee = new Employee("Administrator","Administrator","admin","password",new Date(),"Administrator");
            employee.addGroup("admin");
            employee.addGroup("csr"); 
            userService.createUser(employee);
        }
        if(userService.getUser("tester") == null) {
            BazaarAccount tester = new BazaarAccount("High","Bidder","tester","password",null, new Date(),true);
            tester.addGroup("bidder");
            tester.addGroup("seller");
            userService.createUser(tester);
        }
        if(userService.getUser("supportA") == null) {
            Employee employee = new Employee("Administrator","Administrator","supportA","password",new Date(),"Administrator");
            employee.addGroup("csr"); 
            userService.createUser(employee);
        }
        if(userService.getUser("supportB") == null) {
            Employee employee = new Employee("Administrator","Administrator","supportB","password",new Date(),"Administrator");
            employee.addGroup("csr"); 
            userService.createUser(employee);
        }
        
        // Add a default category
        List<Category> categories = itemManager.getCategories();
        if(categories.isEmpty()) {
            itemManager.createCategory(new Category("Sailboat",new String[] {"sailing","boating","water"}));
            itemManager.createCategory(new Category("Soccer",new String[] {"sports","summer","ball"}));
        }
        // Insert some starter items into the system
        if(itemManager.getItemCount() == 0) {
            itemManager.save(new Item("J30",new Date(), new Date(), new Date(), new BigDecimal("100")));
            itemManager.save(new Item("Sunfish",new Date(), new Date(), new Date(), new BigDecimal("100")));
        }  
    }
    
    
    
}
