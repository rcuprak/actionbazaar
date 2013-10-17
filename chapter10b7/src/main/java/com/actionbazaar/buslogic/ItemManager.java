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

import com.actionbazaar.StarRating;
import com.actionbazaar.account.BazaarAccount;
import com.actionbazaar.account.BazaarAccount_;
import com.actionbazaar.account.User_;
import com.actionbazaar.model.Bid;
import com.actionbazaar.model.Bid_;
import com.actionbazaar.model.Category;
import com.actionbazaar.model.Item;
import com.actionbazaar.model.Item_;
import com.actionbazaar.model.Order;
import com.actionbazaar.model.Order_;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

/**
 * Manages items
 * @author Ryan Cuprak
 */
@Stateless
public class ItemManager {
    
    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("ItemManager");
    
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
    /**
     * Entity manager context
     */
    private EntityManager entityManager;
    
    
    
    @PostConstruct
    private void init() {
        entityManager = entityManagerFactory.createEntityManager();
       
    }
    
    public Item updateItem(Item item) {
        entityManager.joinTransaction();
        entityManager.merge(item);
        return item;
    }
    
    @PreDestroy
    private void cleanup() {
        if(entityManager.isOpen()) {
            entityManager.close();
        }
    }
    /**
     * Adds an item
     * @param name - name
     * @param description - description
     * @param picture - picture
     * @param initialPrice - initial price
     * @param sellerId - seller id
     */
    public void addItem(String name, String description, byte[] picture, 
            BigDecimal initialPrice, long sellerId) {
        Item item = new Item();
        item.setItemName(name);
        item.setPicture(picture);
        item.setInitialPrice(initialPrice);
        BazaarAccount seller = entityManager.find(BazaarAccount.class,sellerId);
        item.setSeller(seller);
        entityManager.persist(item);
    }
    
    public void saveItem(Item item) {
        entityManager.persist(item);
    }
    
    public Item findItem(Long itemId) {
        return null;
    }
    
    /**
     * Deletes the item
     * @param item - item
     */
    public void deleteItem(Item item) {
        entityManager.remove(entityManager.merge(item));
    }

    /**
     * Update items
     * @param item - item
     * @return Item
   
    public Item updateItem(Item item) {
        entityManager.merge(item);
        return item;
    }
    *   */

    /**
     * Finds an item by name
     * @param name - name
     */
    public List<Item> findItemByName(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery(Item.class);
        Root<Item> root = query.from(Item.class);
      
        Path<Date> dateCreatedPath = root.get(Item_.createdDate);
        Predicate startDate = builder.lessThanOrEqualTo(dateCreatedPath,new Date());
        
        
        Predicate condition = builder.like(root.get(Item_.itemName),name);
        
        query.where(condition);
        
        TypedQuery<Item> q = entityManager.createQuery(query); 
        return q.getResultList();
    }
    
    /**
     * Finds items between the specified dates
     * @param startDate
     * @param endDate
     * @return 
     */
    public List<Item> findByDate(Date startDate , Date endDate) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery(Item.class);
        Root<Item> itemRoot = query.from(Item.class);
        Path<Date> datePath = itemRoot.get(Item_.createdDate);
        Predicate dateRangePred = builder.between(datePath,startDate, endDate);
        query.where(dateRangePred);
        TypedQuery<Item> q = entityManager.createQuery(query); 
        return q.getResultList();
    }
    
    
    /**
     * Returns all items in the system.
     * @return 
     */
    public List<String> getAllItemsNames() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = builder.createQuery(String.class);
        Root<Item> root = query.from(Item.class);
        query.select(root.get(Item_.itemName));
        TypedQuery<String> tq = entityManager.createQuery(query);
        return tq.getResultList();
    }
    
        public List<Item> getAllItems() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        entityManager.createStoredProcedureQuery("");
        CriteriaQuery<Item> query = builder.createQuery(Item.class);
        Root<Item> root = query.from(Item.class);
        query.select(root);
        TypedQuery<Item> tq = entityManager.createQuery(query);
        return tq.getResultList();
    }
    
    public List<Object[]> getAllItemNamesWithIds() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Item> root = query.from(Item.class);
        query.select(builder.array(root.get(Item_.itemId),root.get(Item_.itemName)));
        TypedQuery<Object[]> tq = entityManager.createQuery(query);
        return tq.getResultList(); 
    }
    
    
    
    
    
    public void scratchQuery() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Item> itemRoot = query.from(Item.class);
        Root<Bid> bidRoot = query.from(Bid.class);
        
        Path<Long> idItem = itemRoot.get( Item_.itemId );
        Path<Long> idBid =  bidRoot.get( Bid_.id);
        query.select( builder.array( idItem, idBid ) );
        
        
        TypedQuery q = entityManager.createQuery(query);
        List obj = q.getResultList();
        
        
        logger.info("Object: " + obj);

        
    }
    
    
    public void printPersistenceModel() {
        Metamodel metaModel = entityManager.getMetamodel();
        Set<EntityType<? extends Object>> types = metaModel.getEntities();
        for(EntityType<? extends Object> type : types) {
            logger.log(Level.INFO, "--> Type: {0}", type);
            Set attributes = type.getAttributes();
            for(Object obj : attributes) {
                logger.log(Level.INFO, "Name: {0}", ((Attribute)obj).getName());
                logger.log(Level.INFO, "isCollection: {0}", ((Attribute)obj).isCollection());
                logger.log(Level.INFO, "Name: {0}", ((Attribute)obj).isAssociation());
                logger.log(Level.INFO, "Name: {0}", ((Attribute)obj).getPersistentAttributeType());
            }
            
        }
        
        EntityType<Item> item = metaModel.entity(Item.class);
    }
    
    /**
     * Returns the winning bids - performs a join on the order in order to 
     * find the winning bid.
     * @param item - item
     * @return Bid
     */
    public List<WinningBidWrapper> getWinningBid(Long itemId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WinningBidWrapper> query = builder.createQuery(WinningBidWrapper.class);
        
        Root<Item> itemRoot = query.from(Item.class);
        Root<Order> orderRoot = query.from(Order.class);
        
      //  orderRoot.fetch(Order_.bid,JoinType.INNER);
        
        Root<Bid> bidRoot = query.from(Bid.class);
        Root<BazaarAccount> userRoot = query.from(BazaarAccount.class);
        
        
        Join<Order,Bid> j1 = orderRoot.join(Order_.bid);
        
        Join<Order,Item> j2 = orderRoot.join(Order_.item);
        Join<Order,BazaarAccount> j3 = orderRoot.join(Order_.bidder);

        Path<Long> itemIdPath = itemRoot.get(Item_.itemId);
        
      ///  j2.on(builder.like(itemRoot.get(Item_.itemName),"boat"));
        
        Predicate itemPredicate = builder.equal(itemIdPath,itemId);
        
        query.where(itemPredicate);
        // (String username, BigDecimal amount, String itemName , String itemDescription)
      
        query.select(
            builder.construct(
                WinningBidWrapper.class,
                userRoot.get( BazaarAccount_.username ),
                bidRoot.get( Bid_.bidPrice ),
                itemRoot.get(Item_.itemName) ,
                itemRoot.get(Item_.description)
            )
        );
        
        TypedQuery<WinningBidWrapper> q = entityManager.createQuery(query); 
        return q.getResultList();
    }
    
     public List<Tuple> getWinningBidTuple(Long itemId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = builder.createTupleQuery();
        Root<Item> itemRoot = query.from(Item.class);
        Root<Order> orderRoot = query.from(Order.class);
        Root<Bid> bidRoot = query.from(Bid.class);
        Root<BazaarAccount> userRoot = query.from(BazaarAccount.class);
        Join<Order,Bid> j1 = orderRoot.join(Order_.bid);
        Join<Order,Item> j2 = orderRoot.join(Order_.item);
        Join<Order,BazaarAccount> j3 = orderRoot.join(Order_.bidder);
        Path<Long> itemIdPath = itemRoot.get(Item_.itemId);
        Predicate itemPredicate = builder.equal(itemIdPath,itemId);
        query.multiselect( 
                userRoot.get( BazaarAccount_.username ), 
                bidRoot.get( Bid_.bidPrice ),
                itemRoot.get(Item_.itemName) ,
                itemRoot.get(Item_.description));
        TypedQuery<Tuple> q = entityManager.createQuery(query);
        query.where(itemPredicate); 
        
        List<Tuple> results = q.getResultList();
        for(Tuple result : results) {
            logger.log(Level.INFO, "Item: {0}", result.get(itemRoot.get(Item_.itemName)));
        }
        return q.getResultList();
     }
     
     public void updateItem(Long itemId, byte picture[], String description, StarRating starRating) {
         Item item = this.findItem(itemId);
         item.setPicture(picture);
         item.setDescription(description);
         item.setStarRating(starRating);
         entityManager.createStoredProcedureQuery(description);
     }
     
     public List<Category> findAllCategories() {
         //TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c",Category.class);
         TypedQuery<Category> query = entityManager.createNamedQuery("findAllCategories",Category.class);
         
         
         return query.getResultList();
     }

}
