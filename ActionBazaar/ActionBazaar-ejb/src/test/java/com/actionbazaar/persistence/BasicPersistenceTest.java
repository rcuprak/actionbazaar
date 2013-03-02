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
package com.actionbazaar.persistence;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.EJB;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.spec.jpa.persistence.PersistenceDescriptor;
import org.jboss.shrinkwrap.descriptor.api.spec.jpa.persistence.SchemaGenerationModeType;
import org.jboss.shrinkwrap.descriptor.api.spec.jpa.persistence.TransactionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.actionbazaar.account.Address;
import com.actionbazaar.buslogic.BidService;
import com.actionbazaar.buslogic.ItemService;

/**
 * This test verifies that all of the objects that have been mapped to the database can
 * indeed be persisted.
 * @author Ryan Cuprak
 */

    @RunWith(Arquillian.class)
    public class BasicPersistenceTest {

        @EJB
        private ItemService itemService;

        @Deployment(name="glassfish",testable = true)
        public static WebArchive createDeployment() {
            WebArchive wa = ShrinkWrap.create(WebArchive.class, "test.jar")
                .addPackage(Address.class.getPackage()).addPackage(Bid.class.getPackage())
                .addPackage(BidService.class.getPackage())
                .addAsResource(new StringAsset(
                        Descriptors.create(PersistenceDescriptor.class)
                                .persistenceUnit("actionbazaar")
                                .provider("oracle.toplink.essentials.PersistenceProvider")
                                .transactionType(TransactionType.JTA)
                                .jtaDataSource("jdbc/actionbazaar")
                                .schemaGenerationMode(SchemaGenerationModeType.CREATE_DROP)
                                .exportAsString()), "META-INF/persistence.xml");
            wa.addAsWebInfResource(new File("src/test/resources/web.xml"), "src/test/resources/web.xml");
            return wa;
        }

        @Test
        public void testPersistence() {
            Item item = new Item("itemName",new Date(),new Date(),new BigDecimal("10"));
            itemService.createItem(item);
        }
    }


