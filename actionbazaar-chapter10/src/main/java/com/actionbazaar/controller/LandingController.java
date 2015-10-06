/**
 *  LandingController.java
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
package com.actionbazaar.controller;

import com.actionbazaar.NewItem;
import com.actionbazaar.buslogic.ItemManager;
import com.actionbazaar.model.Item;
import com.actionbazaar.util.PerformanceMonitor;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Main landing page.
 * @author Ryan Cuprak
 */
@Named
@ApplicationScoped
@PerformanceMonitor
public class LandingController {
    
    /**
     * Handles basic CRUD operations
     */
    // @Inject - used constructor injection instead
    private ItemManager itemManager;
    
    /**
     * Newest items
     */
    private List<Item> newestItems;
    
    /**
     * Default constructor for the container
     */
    protected LandingController() {}
    
    /**
     * Inject ItemManager using the constructor
     * @param itemManager - item manager
     */
    @Inject
    public LandingController(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
    
    /**
     * Caches the newest items so that we don't bury the database
     */
    @PostConstruct
    public void init() {
        newestItems = itemManager.getNewestItems();
    }
    
    /**
     * Adds a new item
     * @param item - item
     */
    public void onNewItem(@Observes @NewItem Item item) {
        newestItems.add(0,item);
    }
    
    @PreDestroy
    public void destroy() {
        
    }
    
    /**
     * Returns the most recent items
     * @return items
     */
    public List<Item> getNewestItems() {
        return newestItems;
    }
 
}
