package com.actionbazaar.buslogic;


import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebService;
import com.actionbazaar.persistence.Bid;
import com.actionbazaar.ws.PlaceBidWS;

@Stateless
@WebService(endpointInterface = "com.actionbazaar.ws.PlaceBidWS")

public class PlaceBid implements PlaceBidWS{

    @Override
    public long submitBid(long userId, long itemId, double bidPrice) {
        System.out.println("Invoked!");
        return 0;
    }

    public List<Bid> getBids() {
        return null;
    }
}
