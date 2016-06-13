/**
 *  EJB 3 in Action
 *  Book: http://www.manning.com/panda/
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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.sql.DataSource;

import com.actionbazaar.persistence.Bid;
import com.actionbazaar.persistence.Item;

/**
 * Manages bids
 */
@Stateless(name = "BidManager")
@WebService(endpointInterface="com.actionbazaar.buslogic.BidManagerWS")
public class BidManagerBean implements BidManager, BidManagerRemote {

    /**
     * Sessions context
     */
    @Resource
    private SessionContext sc;
    
    /**
     * Creates a data source. see {@code com.actionbazaar.buslogic.SystemInitializer}
     */
    @Resource(name = "java:app/jdbc/ActionBazaarDS")
    private DataSource dataSource;
    
    private Connection connection;

    /**
     * Initializes the BidManager
     */
    @PostConstruct
    public void initialize() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Adds a new bid
     * @param bid - bid
     * @return bid identifier
     */
    public Long addBid(Bid bid) {
      return null;
    }

    /**
     * Returns the bid identifier
     * @return bid identifier
     */
    private Long getBidId() {
        // Add Code for generating a unique key
        return Long.valueOf(1002);
    }

    /**
     * Cancels the bid
     * @param bid - bid
     */
    public void cancelBid(Bid bid) {
    }

    /**
     * Returns the bids
     * @param item - item
     * @return list<bid>
     */
    public List<Bid> getBids(Item item) {
        return item.getBids();
    }

    /**
     * Cleans-up
     */
    @PreDestroy
    public void cleanup() {
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        connection = null;
    }
    /**
     * Returns the bid
     * @param bidId - bid id
     * @return Bid
     */
    @Override
    public String getBid(long bidId) {
        Bid bid = new Bid();
        return "Hello World";
    }

    /* (non-Javadoc)
     * @see com.actionbazaar.buslogic.BidManager#getBidders()
     */
    @Override
    public List<List<String>> getBidders() {
        List<List<String>> result = new LinkedList<>();
        try {
            Statement statement= connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from BIDDERS");
            while (rs.next()) {
                ArrayList<String> Bidder = new ArrayList<>(3);
                Bidder.add(rs.getString(1));
                Bidder.add(rs.getString(2));
                Bidder.add(rs.getString(3));
                result.add(Bidder);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return result;
    }
    
    
}