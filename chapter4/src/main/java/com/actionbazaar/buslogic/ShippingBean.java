/**
 *  ShippingBean.java
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

import com.actionbazaar.account.Address;
import com.actionbazaar.model.Item;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * ShippingBean - demonstrates the use of a a JMS queue.
 */
@Stateless
public class ShippingBean {

    /**
     * Connection Factory
     */
    @Resource(name = "jms/QueueConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    /**
     * JMS destination
     */
    @Resource(name = "jms/ShippingRequestQueue")
    private Destination destination;
    
    /**
     * JMS Connection
     */
    private Connection jmsConnection;
    
    /**
     * Establishes the connection with JMS
     */
    @PostConstruct
    public void initialize() {
        try {
            jmsConnection = connectionFactory.createConnection();
        } catch (JMSException ex) {
            Logger.getLogger(ShippingBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Cleans-up and closes the connection to JMS
     */
    @PreDestroy
    public void cleanup() {
        try {
            jmsConnection.close();
        } catch (JMSException ex) {
            Logger.getLogger(ShippingBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    /**
     * Ships an item - puts the request into a queue which will be routed to the
     * shipper.
     *
     * @param item - item being shipped
     * @param address - address it is being shipped
     * @param method - shipping method being used
     * @param insuranceAmount - insurance amount
     */
    public void shipItem(Item item, Address address, ShippingMethod method, 
            BigDecimal amount) {
        Session session = null;
        try {
            session = jmsConnection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            ObjectMessage message = session.createObjectMessage();
            ShippingRequest shippingRequest = new ShippingRequest();
            shippingRequest.setItem(item);
            shippingRequest.setShippingAddress(address);
            shippingRequest.setShippingMethod(method);
            shippingRequest.setInsuranceAmount(amount);
            message.setObject(shippingRequest);
            producer.send(message);
        } catch (JMSException ex) {
            Logger.getLogger(ShippingBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
