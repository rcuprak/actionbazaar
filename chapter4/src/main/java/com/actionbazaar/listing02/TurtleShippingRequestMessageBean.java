/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.actionbazaar.listing02;

import com.actionbazaar.listing01.ActionBazaarShippingRequest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author <a href="mailto:mjremijan@yahoo.com">Michael Remijan</a>
 */
@MessageDriven(activationConfig = {
  @ActivationConfigProperty(propertyName = "destinationType", 
    propertyValue = "javax.jms.Queue"),
  @ActivationConfigProperty(propertyName = "destinationLookup", 
    propertyValue = "jms/ShippingRequestQueue")
})
public class TurtleShippingRequestMessageBean
  implements MessageListener {

  @PersistenceContext()
  private EntityManager entityManager;
  
  @Override
  public void onMessage(Message message) {
    try {
      ObjectMessage om = (ObjectMessage) message;
      Object o = om.getObject();
      ActionBazaarShippingRequest sr = (ActionBazaarShippingRequest) o;
      Logger.getLogger(TurtleShippingRequestMessageBean.class.getName())
        .log(Level.INFO, String.format("Got message: %s", sr));
    
      TurtleShippingRequest tr = new TurtleShippingRequest();      
      tr.setInsuranceAmount(sr.getInsuranceAmount());
      tr.setItem(sr.getItem());
      tr.setShippingAddress(sr.getShippingAddress());
      tr.setShippingMethod(sr.getShippingMethod());
      entityManager.persist(tr);      

    } catch (JMSException ex) {
      Logger.getLogger(TurtleShippingRequestMessageBean.class.getName())
        .log(Level.SEVERE, null, ex);
    }
  }
}
