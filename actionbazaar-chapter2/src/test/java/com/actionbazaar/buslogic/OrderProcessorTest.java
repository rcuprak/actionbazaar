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
package com.actionbazaar.buslogic;

import com.actionbazaar.persistence.*;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class OrderProcessorTest {

	/**
	 * Creates a deployment item.
	 * 
	 * @return ShrinkWrap
	 */
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(
						// com.actionbazaar.buslogic
						BillingException.class,
						OrderProcessor.class,
						OrderProcessorBean.class,
						ItemService.class,
						ItemServiceBean.class,
						UserService.class,
						UserServiceBean.class,
						// com.actionbazaar.persistence
						Address.class, 
						Bid.class, 
						Bidder.class, 
						Billing.class, 
						Item.class, 
						Order.class,
						OrderStatus.class, 
						Shipping.class, 
						User.class)
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("jbossas-ds.xml");
	}
	/**
	 * Order processor
	 */
	@EJB
	private OrderProcessor orderProcessor;

	/**
	 * Item service
	 */
	@EJB
	private ItemService itemService;

	/**
	 * User service
	 */
	@EJB
	private UserService userService;

	/**
	 * Test processing an order
	 */
	@Test
	public void testOrderProcessor() {
		// set things up
		Item item = new Item("Apple IIGS", new Date(), new Date(), 45.0f);
		itemService.createItem(item);
		Bidder bidder = new Bidder("John", "Wesley Powell", 1869l);
		userService.createUser(bidder);
		Long itemId = item.getItemId();
		Long userId = bidder.getBidderId();

		bidder = (Bidder) userService.getUser(userId);

		// Test item
		item = itemService.getItem(itemId);

		orderProcessor.setBidder(bidder);
		orderProcessor.setItem(item);

		// Get the shipping history of the test bidder
		List<Shipping> shippingChoices = orderProcessor.getShippingChoices();
		Assert.assertNotNull(shippingChoices);

		// Choose the first one in the list
		orderProcessor.setShipping(shippingChoices.get(0));

		// Get the billing history of the test bidder
		List<Billing> billingChoices = orderProcessor.getBillingChoices();

		// Choose the first one in the list
		orderProcessor.setBilling(billingChoices.get(0));

		// Finish the workflow and end the stateful session
		orderProcessor.placeOrder();
	}
}
