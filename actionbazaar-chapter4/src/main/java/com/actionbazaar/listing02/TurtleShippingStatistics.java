package com.actionbazaar.listing02;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class TurtleShippingStatistics {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<TurtleShippingRequest> getPendingRequests(){
        Query q = entityManager.createQuery("select r from TurtleShippingRequest r");
        
        @SuppressWarnings("unchecked")
        List<TurtleShippingRequest> resultList = (List<TurtleShippingRequest>) q.getResultList();
        System.out.println(resultList);
        return resultList;
    }

}
