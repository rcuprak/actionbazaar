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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.actionbazaar.account.Bidder;
import com.actionbazaar.credit.CreditCardSystemException;
import com.actionbazaar.credit.CreditProcessingException;
import com.actionbazaar.persistence.Bid;
import com.actionbazaar.persistence.CreditCard;
import com.actionbazaar.persistence.Item;

/**
 * This bean is responsible for managing bids on items. It has methods for both placing a bid as well
 * as listing bids.
 * @author Ryan Cuprak
 */
// @WebService(endpointInterface = "com.actionbazaar.service.BidServiceWS")
@Stateless(name="BidService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BidServiceBean implements BidService {

    /**
     * Session context
     */
    @Resource
    private SessionContext context;

    /**
     * Logger
     */
    private Logger logger = Logger.getLogger("BidManagerBean");

    /**
     * Persistence Context
     */
    @PersistenceContext(unitName="actionbazaar")
    private EntityManager entityManager;

    /**
     * Credit Card Manager
     */
    @Inject
    private CreditCardManager creditCardManager;

    /**
     * Order Processor
     */
    @Inject
    private OrderProcessor orderProcessor;

    @Resource
    private TimerService timerService;

    /**
     * Adds a new bid
     * @param bid - bid
     */
    public void addBid(Bid bid) {
        timerService.createTimer(15*60*1000,15*60*1000,bid);
        entityManager.persist(bid);
    }

    /**
     * Returns the bid identifier
     * @return bid identifier
     */
    private Long getBidId() {
        // Add Code for generating a unique key
        return Long.valueOf(1003);
    }

    @Interceptors(SecurityInterceptor.class)
    public void cancelBid(Bid bid) {
        if(!context.isCallerInRole("CSR") &&
                !context.isCallerInRole("ADMIN") &&
                (!bid.getBidder().getUsername().equals(
                        context.getCallerPrincipal().getName()) &&
                bid.getBidDate().getTime() >= (new Date().getTime() - 60*1000))) {
            throw new SecurityException("You do not have permission to cancel an order.");

        }
    }

    /**
     * Returns the bids
     * @param item - item
     * @return list<bid>
     */
    @PermitAll
    @WebMethod(exclude = true)
    public List<Bid> getBids(Item item) {
        return item.getBids();
    }

    /**
     * Returns the bid
     * @param bidId - bid id
     * @return Bid
     */
    @Override
    @WebMethod(exclude = true)
    public String getBid(long bidId) {
        Bid bid = new Bid();
        return "Hello World";
    }

    /**
     * Returns true if there are bids on the specified item
     * @return true if there are bids on the item
     */
    @WebMethod(exclude = true)
    protected boolean hasBids(Item item) {
        // TODO finish implementing
        return false;
    }

    /**
     * Marks an item as having been sold
     * @param item
     * @param bidder
     */
    @WebMethod(exclude = true)
    protected void closeBid(Item item, Bidder bidder, BigDecimal amountPaid) {

    }

    @WebMethod(exclude = true)
    public void placeSnagItOrder(Item item, Bidder bidder, CreditCard card) {
        try {
            if(!hasBids(item)) {
                creditCardManager.validateCard(card);
                creditCardManager.chargeCreditCard(card,item.getInitialPrice());
                closeBid(item,bidder,item.getInitialPrice());
            }
        } catch (CreditProcessingException ce) {
            logger.log(Level.SEVERE,"An error ocurred processing the order.",ce);
            context.setRollbackOnly();
        } catch (CreditCardSystemException ccse) {
            logger.log(Level.SEVERE,"Unable to validate credit card.",ccse);
            context.setRollbackOnly();
        }
    }


    @Timeout
    @WebMethod(exclude = true)
    public void monitorBid(Timer timer) {
        Bid bid = (Bid)timer.getInfo();

    }

    @Schedules(
            {@Schedule(hour="16",dayOfWeek="Mon-Thu"),
            @Schedule(hour="16",dayOfWeek="Mon-Thu")})
    public void broadcastNewBids() {

    }


}
