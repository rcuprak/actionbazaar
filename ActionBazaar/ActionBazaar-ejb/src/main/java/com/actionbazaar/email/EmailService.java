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
package com.actionbazaar.email;

import java.util.List;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.mail.HtmlEmail;
import com.actionbazaar.persistence.Email;

/**
 * Message driven bean that handles blasting out emails.
 * @author Ryan Cuprak
 */
@MessageDriven(  mappedName = "jms/emailQueue",
        activationConfig = {
                @ActivationConfigProperty(
                        propertyName="destinationType",
                        propertyValue="javax.jms.Queue"
                ),
                @ActivationConfigProperty(
                        propertyName="destinationName",
                        propertyValue="jms/emailQueue"
                )
        }
)
public class EmailService implements MessageListener {

    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("EmailService");

    /**
     * Persistence Context
     */
    @PersistenceContext(unitName="actionbazaar")
    private EntityManager entityManager; 


    /**
     * Email Session
     */
    @Resource(name="mail/broadcast")
    private Session mailSession;

    /**
     * Directory containing attachments for emails
     */
  // TODO  @Resource(lookup="speaker_picture")
    private String attachmentDirectory;

    /**
     * Sends out an email
     * @param message - message to be sent
     */
    public void onMessage(Message message) {

        try {
            EmailRequest emailRequest = (EmailRequest)((ObjectMessage)message).getObject();
            
            Query query = entityManager.createQuery("select e from Email e where e.action = ?1");
            query.setParameter(1,emailRequest.getAction());
            List results = query.getResultList();
            if(results.size() != 1) {
                logger.severe("A total of " + results.size() + " email templates were returned for action " +
                        emailRequest.getAction());
                return;
            }
            System.out.println("--> Sending email.");
            Email emailInfo = (Email)results.get(0);
            logger.info("Speaker Directory: " + attachmentDirectory);
            HtmlEmail email = new HtmlEmail();
            email.setMailSession(mailSession);
            email.setTextMsg("Hello World!");
          //  email.setSubject(template.getSubject());
            email.addTo("rcuprak@mac.com");
            email.setFrom("java@ctjava.org");
          //  email.send();
            
     /*       emailTemplate.process(emailRecipient.getReplacementTokens());
                        for (Map.Entry<String,File> entry : emailTemplate.getCidMappings().entrySet()) {
                            email.embed(entry.getValue(),entry.getKey());
                        }
                        email.setHtmlMsg(emailTemplate.getPatchedEmail());
       */
        } catch(Throwable t) {
            t.printStackTrace();
            //context.setRollbackOnly();
        }
    }



}
