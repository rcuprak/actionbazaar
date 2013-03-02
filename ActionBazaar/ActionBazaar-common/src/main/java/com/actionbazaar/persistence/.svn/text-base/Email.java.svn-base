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
package com.actionbazaar.persistence;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents an email.
 * @author Ryan Cuprak
 */
@Entity
@Table(name = "EMAIL")
public class Email implements Serializable {

    /**
     * Email identifier
     */
    @Id
    @Column(name="EMAIL_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @TableGenerator(name = "ITEM_TABLE_GEN", table = "sequence",
            pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
            pkColumnValue = "ITEM_SEQ")
    private Long emailId;

    /**
     * Function of the email
     */
    @NotNull
    @Size(min=3,max=45)
    private String action;

    /**
     * Email subject
     */
    @NotNull
    @Size(min=3,max=45)
    private String subject;
    
    /**
     * Html content of the email
     */
    @NotNull
    @Lob
    private String htmlContent;

    /**
     * Plain content
     */
    @NotNull
    @Lob
    private String plainContent;

    /**
     * Default constructor for JPA
     */
    public Email() {
        // default constructor for JPA
    }

    /**
     * Constructs a new email
     * @param action - purpose of the email
     * @param subject - subject of the email
     * @param htmlContent - html content of the email
     */
    public Email(String action, String subject, String htmlContent) {
        this.action = action;
        this.subject = subject;
        this.htmlContent = htmlContent;
    }

    /**
     * Returns the email id
     * @return email id
     */
    public Long getEmailId() {
        return emailId;
    }

    /**
     * Sets the email id
     * @param emailId - email id
     */
    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    /**
     * Returns the action (purpose) of the email
     * @return email function
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the function of the email
     * @param action - email action
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Returns the subject to be used on the email
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject on the email
     * @param subject - email subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Returns the raw content of the email
     * @return email content
     */
    public String getHtmlContent() {
        return htmlContent;
    }

    /**
     * Sets the email content
     * @param htmlContent - email content
     */
    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    /**
     * Returns the plain content
     * @return plain content
     */
    public String getPlainContent() {
        return plainContent;
    }

    /**
     * Sets the plain content
     * @param plainContent - plain content
     */
    public void setPlainContent(String plainContent) {
        this.plainContent = plainContent;
    }
}
