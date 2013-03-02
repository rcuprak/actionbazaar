package com.actionbazaar.ws;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC,use=SOAPBinding.Use.ENCODED)
public interface PlaceBidWS {

    public long submitBid(long userId, long itemId, double bidPrice);

}
