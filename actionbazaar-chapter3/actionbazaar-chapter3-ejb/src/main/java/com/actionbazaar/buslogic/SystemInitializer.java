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
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;

/**
 * Initializes application logging.
 */
@Singleton
@Startup
@Asynchronous
public class SystemInitializer {
	
	/**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("SystemInitializer");
	
    /**
     * Creates a data source
     * The datasource is defined in glassfish-resources.xml in the EAR.
     * This is a DERBY in-memory database. 
     */
    @Resource(name = "java:app/jdbc/ActionBazaarDS")
    private DataSource dataSource;
    
    /**
     * Connection info
     */
    private Connection connection;

	private final String createBiddersTable = "create table BIDDERS ("
									+ "username varchar(10) PRIMARY KEY,"
									+ "first_name varchar(30) NOT NULL,"
									+ "credit_card_type varchar(20) NOT NULL"
									+ ")";

	private final String createBidsTable = "create table BIDS (" 
									+ "BID_ID numeric(19) PRIMARY KEY,"
									+ "BID_DATE DATE NOT NULL,"
									+ "BID_STATUS varchar(20) NOT NULL,"
									+ "BID_PRICE numeric(19,4) NOT NULL,"
									+ "BID_ITEM_ID numeric(19) NOT NULL,"
									+ "BID_BIDDER varchar(45) NOT NULL"
									+ ")";
    
    @PostConstruct
    public void initDatabaseSchema() {
    	try {
    		
    		logger.info("*** creating database for ActionBazaar Application ***");
			
    		connection = dataSource.getConnection();
			DatabaseMetaData metaData = connection.getMetaData();
			Statement statement = connection.createStatement();

			ResultSet rs = metaData.getTables(null, "APP", "BIDDERS", null);
			if (rs.next()) { 
				// The table exists.
				// Since this is a study/test application, 
				// we cleanup all pre-existing data if any. 
				statement.execute("delete from BIDDERS");
			} else {
				statement.execute(createBiddersTable);
			}
			
			rs = metaData.getTables(null, "APP", "BIDS", null);
			if (rs.next()) { 
				// The table exists.
				// Since this is a study/test application, 
				// we cleanup all pre-existing data if any. 
				statement.execute("delete from BIDS");
			} else {
				statement.execute(createBidsTable);
			}
			statement.close();
			
			//connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    }
    
    @PreDestroy
    public void cleanup() {
    	logger.info("*** Application shutting down. Dropping tables ***");
        try {
            // there is a bug that causes the next statement to crash
            // https://java.net/jira/browse/GLASSFISH-21476
        	connection = dataSource.getConnection();
        	
        	// Since this is just a sample application, 
        	// drop the tables when the application is removed.
        	// This is not usually done in production applications.
        	Statement statement = connection.createStatement();
        	statement.execute("drop table BIDDERS");
        	statement.execute("drop table BIDS");

        	logger.info("*** Dropped tables BIDDERS and BIDS ***");
            connection.close();
            connection = null;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Asynchronous
    public void updateDatabaseSchema() {

    }

}
