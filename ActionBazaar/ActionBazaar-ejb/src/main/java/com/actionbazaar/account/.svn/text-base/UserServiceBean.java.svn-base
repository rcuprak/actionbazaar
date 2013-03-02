/**
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
package com.actionbazaar.account;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.actionbazaar.email.EmailRequest;
import com.actionbazaar.service.UserService;
import com.actionbazaar.ws.UserServiceWS;
import com.actionbazaar.ws.dto.CreateUserResponse;
import com.actionbazaar.ws.dto.UserDTO;

/**
 * Manages users
 * Since we are running in the same container, this interface isn't necessary for
 * EJB 3.1 unless we wanted to "hide" public methods.
 */
@Stateless(name="UserService")
@WebService(endpointInterface = "com.actionbazaar.ws.UserServiceWS")
public class UserServiceBean implements UserService, UserServiceWS {

    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("UserServiceBean");
    
    /**
     * JMS connection factory
     */
    @Resource(name="jms/QueueConnectionFactory")
    private ConnectionFactory connectionFactory;

    /**
     * JMS destination
     */
    @Resource(name="jms/emailQueue")
    private Destination destination;

    /**
     * Persistence Context
     */
    @PersistenceContext(unitName="actionbazaar")
    private EntityManager entityManager;

    /**
     * Email Id
     */
    private Long joinEmailId;

    /**
     * Retrieves a bidder from the database
     * @param userId - user id
     * @return User
     */
    @Override
    public User getUser(long userId) {
        return entityManager.find(Bidder.class,userId);
    }

    /**
     * Retrieves the user given the username
     * @param username - username
     * @return User
     */
    @Override
    public User getUser(String username) {
        Query q = entityManager.createQuery("select u from User u where u.username = :username");
        q.setParameter("username",username);
        return (User)q.getSingleResult();
    }

    /**
     * Creates a new user in the system
     * @param user - user
     */
    @Override
    public void createUser(User user) {
        // encode password
        String uid = UUID.randomUUID().toString();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        user.addActivationLink(new ActivationLink(uid,"activate-account",calendar.getTime()));
        entityManager.persist(user);
        // If the user's account is not verified, then we want to send an email.
        if(!user.isAccountVerified()) {
            Connection connection = null;
            Session session = null;
            try {
                connection = connectionFactory.createConnection();
                session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = session.createProducer(destination);
                ObjectMessage objectMessage = session.createObjectMessage();

                Map<String,String> tokens = new HashMap<String,String>();
                tokens.put("email",user.getEmail());
                tokens.put("firstName",user.getFirstName());
                tokens.put("lastName",user.getLastName());

                EmailRequest emailRequest = new EmailRequest(user.getEmail(),tokens,"email-activation");
                objectMessage.setObject(emailRequest);
                messageProducer.send(objectMessage);
            } catch (JMSException e) {
                logger.log(Level.SEVERE,e.getMessage(),e);
                if(session != null) try { session.rollback(); } catch (Throwable t) {}
            } finally {
                if(session != null) try { session.close(); } catch (Throwable t) {}
                if(connection != null) try { connection.close(); } catch (Throwable t) {}
            }
        }
    }


    @Override
    public CreateUserResponse createUser(UserDTO user) {
        logger.info("Invoked!!");
        return null;
    }
}
