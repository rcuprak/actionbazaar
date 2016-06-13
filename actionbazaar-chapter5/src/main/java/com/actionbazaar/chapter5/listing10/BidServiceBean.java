package com.actionbazaar.chapter5.listing10;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
public class BidServiceBean implements BidService {
    @Interceptors(ActionBazaarLogger.class)
    public void addBid(Bid bid) {
    }
}

