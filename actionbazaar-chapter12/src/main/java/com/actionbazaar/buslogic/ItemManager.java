/**
 *  FlyerBean.java
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

import com.actionbazaar.model.Category;
import com.actionbazaar.model.Item;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Basic CRUD operations for items.
 * @author Ryan Cuprak
 */
@Stateless
public class ItemManager {
    
    /**
     * Entity Manager
     */
    @PersistenceContext
    private EntityManager entityManager;
    
    /**
     * Creates a category
     * @param category - category to be created
     */
    public void createCategory(Category category) {
        entityManager.persist(category);
    }
    
    /**
     * Returns a list of categories within the system
     */
    public List<Category> getCategories() {
        TypedQuery<Category> query = entityManager.createNamedQuery("Categories.findAll",Category.class);
        return query.getResultList();
    }
    
    /**
     * Returns a count of the number of items in the system
     * @return item count
     */
    public long getItemCount() {
        Query query = entityManager.createNamedQuery("Item.count",Category.class);
        return (Long)query.getSingleResult();
    }
    
    /**
     * Returns the item specified by the itemId
     * @param itemId - item id
     */
    public void getItem(Long itemId) {
        
    }
    
    /**
     * Saves an item to the database
     * @param item - item to be save
     */
    public void save(Item item) {
        entityManager.persist(item);
    }
    
    /**
     * Returns the newest items
     * @return newest items
     */
    public List<Item> getNewestItems() {
        return list();
    }
    
    /**
     * Returns a list of items  Item.all
     * @return items
     */
    public List<Item> list() {
        TypedQuery<Item> query = entityManager.createNamedQuery("Item.all",Item.class);
        return query.getResultList();
    }
    
    /**
     * Creates a new item - persists it in the database
     * @param item 
     */
    public void createItem(Item item) {
        
    }
    
}
