package com.actionbazaar.test;

import com.actionbazaar.account.Address;
import com.actionbazaar.account.BazaarAccount;
import com.actionbazaar.account.UserService;
import com.actionbazaar.buslogic.ItemManager;
import com.actionbazaar.buslogic.OrderManager;
import com.actionbazaar.buslogic.WinningBidWrapper;
import com.actionbazaar.model.Bid;
import com.actionbazaar.model.CreditCard;
import com.actionbazaar.model.CreditCardType;
import com.actionbazaar.model.Item;
import com.actionbazaar.model.Order;
import com.actionbazaar.model.Shipping;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.Tuple;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Ryan Cuprak
 */
@Singleton
@Startup
public class TestBean {
    
    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("TestBean");
    
    /**
     * Responsible for managing items
     */
    @Inject
    private ItemManager itemManager;
    
    @Inject
    private UserService userService;
    
    @Inject
    private OrderManager orderManager;
    

    @PostConstruct
    public void init() {
      //  BazaarAccount ba = new BazaarAccount("Ryan","Cuprak","rcupra","password",null,new Date(),true);
      //  userServiceBean.createUser(ba);
        
      //  Bid bid = new Bid();
      //  bid.setBidPrice(new BigDecimal("34"));
      //  bidManager.save(bid);
   //     itemManager.addItem("Boat","sails", null, BigDecimal.ZERO, ba.getUserId());
     
      

        BazaarAccount ba = new BazaarAccount("Ryan","Cuprak","rcuprak","password",null, new Date(), true);
        userService.createUser(ba);
        
        Item item = new Item("Sailboat", new Date(), new Date(), new Date(), BigDecimal.ZERO);
        itemManager.saveItem(item);
        item.addBid(new Bid(ba, item,new BigDecimal("10")));
        Bid winningBid = new Bid(ba, item,new BigDecimal("15"));
        item.addBid(winningBid);
        itemManager.updateItem(item);  
        CreditCard card = new CreditCard(ba,"Ryan Cuprak","0000-0000-0000-0000","10","2013","331",CreditCardType.VISA);
        Order order = new Order(item,winningBid,ba,card,new Shipping(new BigDecimal("20")));
        try {
            orderManager.createOrder(order);
        } catch (Throwable t) {
            t.printStackTrace();
            Throwable v = t.getCause();
            while(v != null) {
                v = v.getCause();
                if(v == null) {
                    break;
                }
                if(v instanceof ConstraintViolationException) {
                    ConstraintViolationException c = (ConstraintViolationException)v;
                    for(ConstraintViolation x : c.getConstraintViolations()) {
                        System.out.println("--> " + x.getMessage());
                    }
                }
            }  
        }
        
        List<Tuple> tuples = itemManager.getWinningBidTuple(Long.MIN_VALUE);
        

        List<WinningBidWrapper> bids = itemManager.getWinningBid(item.getItemId());
        for(WinningBidWrapper bid : bids) {
            System.out.println("Winning bid: " + bid);
        }
        System.out.println("Count: " + bids);
       
    }
    
    public void placeBid(Long itemId,BigDecimal bidAmount) {
        Item item = itemManager.findItem(itemId);
        item.addBid(new Bid(bidAmount));
        itemManager.updateItem(item);
    }

}
