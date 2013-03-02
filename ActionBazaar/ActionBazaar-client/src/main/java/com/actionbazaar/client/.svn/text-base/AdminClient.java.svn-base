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
package com.actionbazaar.client;

import java.awt.BorderLayout;
import java.util.Date;
import javax.ejb.EJB;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import com.actionbazaar.State;
import com.actionbazaar.account.Address;
import com.actionbazaar.account.Bidder;
import com.actionbazaar.persistence.CreditCard;
import com.actionbazaar.persistence.CreditCardType;
import com.actionbazaar.service.UserService;

/**
 * AdminClient
 * @author Ryan Cuprak
 */
public class AdminClient extends JFrame {

    /**
     * User Service
     */
    @EJB
    private static UserService userService;

    /**
     * Creates a new AdminClient
     */
    public AdminClient() {
        super("Account Creator Client");
        getContentPane().add(new JLabel("Account Creator Client"), BorderLayout.CENTER);
        pack();
    }

    /**
     * Launches the application
     * @param args
     */
    public static void main(String[] args) {
        Address address = new Address("Market St.","San Francisco", State.California,"00000","555-555-5555");
        CreditCard creditCard = new CreditCard("Debu Panda","5555555555554444","12","2012","000", CreditCardType.MASTERCARD);
        userService.createUser(new Bidder("dpanda","welcome","Debu","Panda",creditCard,address,new Date(),false));
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AdminClient ac = new AdminClient();
                ac.setVisible(true);
            }
        });
    }
}