/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.actionbazaar.web;

import com.actionbazaar.buslogic.BidManager;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Ryan Cuprak
 */
@Stateless
@Path("/bidManager")
public class BidManagerRestful {
    
    @EJB
    private BidManager bidManager;

    @GET
    @Path("{id}")
    @Produces("text/plain")
    public String getBid(@PathParam("id") long bidId) {
        return bidManager.getBid(bidId);
    }
}
