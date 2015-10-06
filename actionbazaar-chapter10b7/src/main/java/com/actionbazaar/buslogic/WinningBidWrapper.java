/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.actionbazaar.buslogic;

import java.math.BigDecimal;

/**
 *
 * @author rcuprak
 */
public class WinningBidWrapper {

    /**
     * first name
     */
    private String username;
    /**
     * Amount
     */
    private BigDecimal amount;
    /**
     * Item name
     */
    private String itemName;
    /**
     * Item description
     */
    private String itemDescription;

    public WinningBidWrapper(String username, BigDecimal amount, String itemName, String itemDescription) {
        this.username = username;
        this.amount = amount;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    
    @Override
    public String toString() {
        return itemName;
    }
    
}
