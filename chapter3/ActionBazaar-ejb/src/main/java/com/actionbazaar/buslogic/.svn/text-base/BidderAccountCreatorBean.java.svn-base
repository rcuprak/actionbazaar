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

import com.actionbazaar.persistence.BillingInfo;
import com.actionbazaar.persistence.BiographicalInfo;
import com.actionbazaar.persistence.LoginInfo;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.sql.DataSource;

/**
 * BidderAccountCreatorBean
 */
@Stateful(name = "BidderAccountCreator")
public class BidderAccountCreatorBean implements BidderAccountCreator {

    /**
     * Creates a data source
     */
    @Resource(name = "jdbc/ActionBazaarDS")
    private DataSource dataSource;

    /**
     * Login info
     */
    private LoginInfo loginInfo;

    /**
     * Biographical info
     */
    private BiographicalInfo biographicalInfo;

    /**
     * Billing info
     */
    private BillingInfo billingInfo;

    /**
     * Connection info
     */
    private Connection connection;

    /**
     * Opens a new connection.
     */
    @PostConstruct
    @PostActivate
    public void openConnection() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * Adds a new login info
     * @param loginInfo - login info
     */
    @Override
    public void addLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    /**
     * Adds a new biographical info
     * @param biographicalInfo - biographical info
     * @throws WorkflowOrderViolationException - thrown if there is an error
     */
    @Override
    public void addBiographicalInfo(BiographicalInfo biographicalInfo)
            throws WorkflowOrderViolationException {
        if (loginInfo == null) {
            throw new WorkflowOrderViolationException(
                    "Login info must be set before biographical info");
        }
        this.biographicalInfo = biographicalInfo;
    }

    /**
     * Adds billing info
     * @param billingInfo - billing info
     * @throws WorkflowOrderViolationException - thrown if there is an error
     */
    @Override
    public void addBillingInfo(BillingInfo billingInfo)
            throws WorkflowOrderViolationException {
        if (biographicalInfo == null) {
            throw new WorkflowOrderViolationException(
                    "Biographical info must be set before billing info");
        }

        this.billingInfo = billingInfo;
    }

    /**
     * Cleans everything up
     */
    @PrePassivate
    @PreDestroy
    public void cleanup() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * Cancels the account creation info
     */
    @Remove
    public void cancelAccountCreation() {
        loginInfo = null;
        biographicalInfo = null;
        billingInfo = null;
    }

    /**
     * Creates the account
     */
    @Remove
    public void createAccount() {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO BIDDERS(" + "username, " + "first_name, "
                    + "credit_card_type" + ") VALUES (" + "'"
                    + loginInfo.getUsername() + "', " +
                    "'" + biographicalInfo.getFirstName() + "', " +
                    "'" + billingInfo.getCreditCardType() + "'" +
                    ")";
            statement.execute(sql);
            statement.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}