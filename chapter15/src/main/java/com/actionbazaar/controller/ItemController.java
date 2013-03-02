/**
 *  ItemController.java
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

import com.actionbazaar.CurrentUser;
import com.actionbazaar.NavigationRules;
import com.actionbazaar.NewItem;
import com.actionbazaar.SelectedItem;
import com.actionbazaar.account.BazaarAccount;
import com.actionbazaar.account.User;
import com.actionbazaar.buslogic.ItemManager;
import com.actionbazaar.model.Item;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Item Controller
 * @author Ryan Cuprak
 */
@Model
public class ItemController implements Serializable {
  
    /**
     * Serial unique id
     */
    private static final long serialVersionUID = 9233723423L;
    
    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("ItemController");
    
    /**
     * Handles basic CRUD operations
     */
    @EJB
    private ItemManager itemManager;
   
    /**
     * Reference to current conversation
     */
    @Inject
    private Conversation conversation;
    
    /**
     * Bid Amount
     */
    private BigDecimal bidAmount;
    
    /**
     * Selected item
     */
    private Item item;
    
    /**
     * Used to send out a broadcast that an item has changed
     */
    @Inject @NewItem
    private Event<Item> itemNotifier;
    
    /**
     * Current user
     */
    @CurrentUser
    private User currentUser;
    
    /**
     * Starts the conversation
     * @param item - item 
     * @return 
     */
    public String startConversation(Item item) {
        conversation.begin();
        this.item = item;
        return NavigationRules.ITEM.getRule();
    }
    
    /**
     * Produces the selected item for the current context
     * @return 
     */
    @Produces @SelectedItem @Named("selectedItem") @ConversationScoped
    public Item getCurrentItem() {
        if(item == null) {
            item = new Item();
        }
        return item;
    }
    
    /**
     * Adds the item
     * @return home if successful
     */
    public String add() {
        logger.log(Level.INFO, "Adding item: {0}", item.getItemName());
        item.setSeller((BazaarAccount)currentUser);
        itemManager.save(item);
        itemNotifier.fire(item);
        return NavigationRules.HOME.getRule();
    }
    
}
