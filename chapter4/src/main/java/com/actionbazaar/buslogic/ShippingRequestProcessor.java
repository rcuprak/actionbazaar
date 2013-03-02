/**
 *  ShippingRequestProcessor.java
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


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.sql.DataSource;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(                                  	 
            propertyName = "destinationName",
    propertyValue = "jms/ShippingRequestQueue")
})
public class ShippingRequestProcessor implements MessageListener {
    
    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("ShippingRequestProcessor");

    /**
     * Database connection
     */
    private Connection connection;
    
    /**
     * DataSource
     */
    private DataSource dataSource;
    
    /**
     * Message context
     */
    @Resource
    private MessageDrivenContext context;

    /**
     * Configures the data source
     * @param dataSource - data source
     */
    @Resource(name = "jdbc/TurtleDS")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    /**
     * Initializes the bean
     */
    @PostConstruct
    public void initialize() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE,"Error acquiring a connection to the database.",sqle);
        }
    }

    @PreDestroy
    public void cleanup() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE,"Error closing the database connection.",sqle);
        }
    }

    /**
     * Processes the incoming message from JMS
     * @param message 
     */
    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            ShippingRequest shippingRequest =
                    (ShippingRequest) objectMessage.getObject();
            processShippingRequest(shippingRequest);
        } catch (JMSException jmse) {
            logger.log(Level.SEVERE,"JMS exception",jmse);
            context.setRollbackOnly();
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE,"SQL exception",sqle);
            context.setRollbackOnly();
        }
    }

    /**
     * Saves a shipping request in the database
     * @param request - shipping request
     * @throws SQLException - SQL exception
     */
    private void processShippingRequest(ShippingRequest request)
            throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO "
                + "SHIPPING_REQUESTS ("
                + "ITEM_ID, "
                + "SHIPPING_ADDRESS, "
                + "SHIPPING_METHOD, "
                + "INSURANCE_AMOUNT ) "
                + "VALUES ( "
                + request.getItem() + ", "
                + "\'" + request.getShippingAddress() + "\', "
                + "\'" + request.getShippingMethod() + "\', "
                + request.getInsuranceAmount() + " )";
        System.out.println("Shipping Request processed...");
        statement.execute(sql);
    }
}
