/**
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
package com.actionbazaar.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.actionbazaar.State;
import com.actionbazaar.account.Address;
import com.actionbazaar.account.Bidder;
import com.actionbazaar.buslogic.FlyerBean;
import com.actionbazaar.persistence.CreditCard;
import com.actionbazaar.persistence.CreditCardType;
import com.actionbazaar.persistence.Email;
import com.actionbazaar.service.UserService;
import com.actionbazaar.util.ResourceBundleKeys;
import com.actionbazaar.web.MessageBundle;
import com.actionbazaar.web.PageNavigationEnum;
import com.actionbazaar.web.PhoneNumber;

/**
 * Manages the creation of a new account.
 * @author Ryan Cuprak
 */
@Named @ConversationScoped
public class BidderAccountController implements Serializable {

    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("BidderAccountController");

    /**
     * ResourceBundle
     * @see com.actionbazaar.web.MessageBundleProvider
     * @see com.actionbazaar.web.MessageBundle
     */
    @Inject @MessageBundle
    private transient ResourceBundle bundle;

    /**
     * Navigation Step - 2
     */
    private static final String NAVIGATION_STEP_2 = "step2";

    /**
     * Navigation Step - 3
     */
    private static final String NAVIGATION_STEP_3 = "step3";


    /**
     * User name for the user
     */
    @Size(min=5,max=30,message="{step1_usernameSize}")
    private String username;

    /**
     * Password
     */
    private String password;

    /**
     * First name
     */
    @Size(min=2,max=30,message="{step2_firstNameSize}")
    private String firstName;

    /**
     * Last name
     */
    @Size(min=2,max=30,message="{step2_lastNameSize}")
    private String lastName;

    /**
     * Address
     */
    private Address address = new Address();

    /**
     * Conversation - this bean is conversationally scoped.
     */
    @Inject
    private Conversation conversation;

    /**
     * User email - creates the account
     */
    @EJB
    private UserService userService;

    /**
     * Password confirmation
     */
    @Size(min=8,max=30,message="{step1_passwordSize}")
    private String passwordConfirm;

    /**
     * Phone number
     */
    private PhoneNumber phoneNumber;

    /**
     * Credit card information
     */
    private CreditCard creditCard = new CreditCard();

    /**
     * True if step 1 has been completed
     */
    private boolean step1;

    /**
     * True if step 2 has been completed
     */
    private boolean step2;

    /**
     * Address
     */
    @Size(min=8,max=30,message="{step1_emailSize}")
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message="{step1_invalidEmail}")
    private String email;

    /**
     * Confirm email
     */
    private String confirmEmail;

    /**
     * Email confirmation component, we will need its ID to mark it if the username/password doesn't match.
     */
    private UIInput emailConfirmComp;

    /**
     * Sets the email confirmation component
     * @param emailConfirmComp - confirmation component
     */
    public void setEmailConfirmComp(UIInput emailConfirmComp) {
        this.emailConfirmComp = emailConfirmComp;
    }

    /**
     * Returns the email confirmation component
     * @return confirmation component
     */
    public UIInput getEmailConfirmComp() {
        return emailConfirmComp;
    }

    /**
     * Returns the user name
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username
     * @param username - username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password
     * @param password - password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the firstname
     * @return firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name
     * @param firstName - first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name
     * @param lastName - last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the address
     * @return address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address
     * @param address - address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Sets the password confirm string
     * @param passwordConfirm - password confirm
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    /**
     * Returns the password confirmation
     * @return password confirmation
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * Returns the email address
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address
     * @param email - email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Confirmation email address
     * @return confirmation email address
     */
    public String getConfirmEmail() {
        return confirmEmail;
    }

    /**
     * Sets the confirmation email address
     * @param confirmEmail - confirmation email address
     */
    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    /**
     * Sets the phone number
     * @param phoneNumber - phone number
     */
    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the phone number
     * @return phone number
     */
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the credit card
     * @param creditCard - credit card
     */
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * Starts the conversation for creating a new account
     */
    @PostConstruct
    public void beginAccountCreateConversation() {
        if(conversation.getId() == null) {
            conversation.begin();
        }
    }
    
    @EJB
    private FlyerBean flyerBean;

    /**
     * Processes the first step which is entry of the login information.
     * @return step2
     */
    public String processFirstStep() {
        if(!email.equals(confirmEmail)) {
            String emailMustMatch = bundle.getString(ResourceBundleKeys.step1_emailMustMatch.getKey());
            FacesContext.getCurrentInstance().addMessage(emailConfirmComp.getClientId(),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,emailMustMatch,emailMustMatch));
            // TODO check the database to see if we already have a user with the same name


            
            return null;
            
        }
        ScheduleExpression se = new ScheduleExpression();
        se.month(2).dayOfMonth(14).year(2012).hour(11).minute(30);

                    flyerBean.scheduleFlyer(se,new Email());
        step1 = true;
        return NAVIGATION_STEP_2;
    }

    /**
     * Process the second step which is entry of the biographical data
     * @return step3
     */
    public String processSecondStep() {
        // Picture upload

        step2 = true;
        return NAVIGATION_STEP_3;
    }

    /**
     * Creates the bidder
     * @return home page
     */
    public String create() {
        if(!step1 || !step2) {
            String txt = bundle.getString(ResourceBundleKeys.step3_skippedSteps.getKey());
            FacesContext.getCurrentInstance().addMessage("",
                new FacesMessage(txt,txt));
            return PageNavigationEnum.CREATE_ACCOUNT.toString();
        }
        String cardType = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("card");
        if(cardType != null) {
            CreditCardType type = CreditCardType.valueOf(cardType);
            if(type == null) {
                String txt = bundle.getString(ResourceBundleKeys.step3_selectCardType.getKey());
                FacesContext.getCurrentInstance().addMessage("",
                        new FacesMessage(txt,txt));
                return null;
            }
        } else {
            String txt = bundle.getString(ResourceBundleKeys.step3_selectCardType.getKey());
            FacesContext.getCurrentInstance().addMessage("",
                    new FacesMessage(txt,txt));
            return null;
        }
        userService.createUser(new Bidder(username,password,firstName,lastName,creditCard,address,new Date(),false));
        conversation.end();
        String txt = bundle.getString(ResourceBundleKeys.step3_confirmationEmail.getKey());
        FacesContext.getCurrentInstance().addMessage("",
                        new FacesMessage(txt,txt));
        return PageNavigationEnum.HOME.toString();
    }

    /**
     * Cancels creation - closes out the bean etc.
     * @return home
     */
    public String cancel() {
        conversation.end();
        return PageNavigationEnum.HOME.toString();
    }

    /**
     * Returns the list of states
     * @return state
     */
    public State[] getStates() {
        return State.values();
    }


}
