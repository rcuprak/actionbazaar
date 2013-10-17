/**
 * ItemManagerTest.java EJB 3 in Action Book: http://manning.com/panda2/ Code:
 * http://code.google.com/p/action-bazaar/ Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.actionbazaar.buslogic;

import com.actionbazaar.State;
import com.actionbazaar.account.Address;
import com.actionbazaar.account.UserService;
import com.actionbazaar.buslogic.exceptions.CreditCardSystemException;
import com.actionbazaar.controller.BidController;
import com.actionbazaar.model.Bid;
import com.actionbazaar.setup.Bootstrap;
import com.actionbazaar.util.FacesContextProducer;
import java.math.BigDecimal;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

/**
 * Item Manager Tests
 *
 * @author Ryan Cuprak
 */
//@RunWith(Arquillian.class)
public class ItemManagerTest {

    /**
     * Entity Manager
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Item Manager
     */
    @Inject
    private ItemManager itemManager;
    
    /**
     * User service
     */
    @Inject
    private UserService userService;
    


    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive wa = ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(State.class.getPackage())
                .addPackage(Address.class.getPackage())
                .addPackage(BidManagerBean.class.getPackage())
                .addPackage(CreditCardSystemException.class.getPackage())
                .addPackage(BidController.class.getPackage())
                .addPackage(Bootstrap.class.getPackage())
                .addPackage(FacesContextProducer.class.getPackage())
                .addPackage(Bid.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(wa.toString(true));
        return wa;
    }
    
    /**
     * Tests the creation of an item
    
    public void testCreateItem() {
            public BazaarAccount(String firstName, String lastName, String username, String password, Address address, Date dateCreated, boolean accountVerified) {
    
        userService.createUser(null);
        itemManager.addItem("Ford Fusion","Car",null,new BigDecimal(24000),12);
    }
    *  */
}
