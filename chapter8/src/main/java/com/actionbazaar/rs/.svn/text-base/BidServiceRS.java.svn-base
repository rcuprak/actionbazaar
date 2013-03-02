/**
 *  BidServiceRS.java
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
package com.actionbazaar.rs;

import javax.ejb.Local;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Restful BidService
 */
@Local @Path("/bidService")							
public interface BidServiceRS {
    
    /**
     * Adds a bid
     * @param bid - bid 
     */
    @POST 									
    @Path("/addBid")							
    @Consumes("application/xml")						
    public void addBid(BidDTO bid);
   
    /**
     * Retrieves a bid
     * @param bidId - bid id
     * @return bid
     */
    @GET 									
    @Path("/getBid/{bidId}")					
    @Produces({"application/json","application/xml"})			
    public BidDTO getBid(@PathParam("bidId") long bidId);			
    
    /**
     * Deletes a bid
     * @param bidId - bid to be deleted
     */
    @DELETE 									
    @Path("delete/{bidId}")
    public void cancelBid(@PathParam("bidId") long bidId);
    
    /**
     * Lists bids
     * @param category - category
     * @param userId - user id
     * @param startDate - start date
     * @param endDate - end date
     * @return 
     */
    @GET
    @Produces("text/plain")
    @Path("/list/{userId}/{category}")
    public String listBids( 
            @PathParam("category")String category,			
            @PathParam("userId")long userId,				
            @QueryParam("startDate") String startDate,		
            @QueryParam("endDate") String endDate);   			
}

