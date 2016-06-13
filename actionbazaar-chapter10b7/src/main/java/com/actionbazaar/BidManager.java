package com.actionbazaar;

import com.actionbazaar.model.Bid;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 * Bid Manager
 * @author Ryan Cuprak
 */
@Stateless
public class BidManager {
    
    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("BidManager");
    
    /**
     * Entity Manager
     */
    @PersistenceContext
    private EntityManager entityManager;
    
    /**
     * Saves a bid to the database
     * @param bid - bid
     */
    public void save(Bid bid) {   
        entityManager.persist(bid);
        StoredProcedureQuery spq = entityManager.createStoredProcedureQuery("getQtyOrders");
        spq.registerStoredProcedureParameter("param1", Integer.class, ParameterMode.IN);
        spq.setParameter("param1",55);
        Object[] count = (Object[])spq.getSingleResult();
        logger.log(Level.INFO, "Single result: {0}", count[0]);
    }
}
