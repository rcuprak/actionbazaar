/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.actionbazaar.listing01;

import java.io.Serializable;

/**
 *
 * @author <a href="mailto:mjremijan@yahoo.com">Michael Remijan</a>
 */
public class ActionBazaarShippingRequest implements Serializable 
{
  private String item;
  private String shippingAddress;
  private String shippingMethod;
  private double insuranceAmount;

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public String getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(String shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public String getShippingMethod() {
    return shippingMethod;
  }

  public void setShippingMethod(String shippingMethod) {
    this.shippingMethod = shippingMethod;
  }

  public double getInsuranceAmount() {
    return insuranceAmount;
  }

  public void setInsuranceAmount(double insuranceAmount) {
    this.insuranceAmount = insuranceAmount;
  }

    @Override
    public String toString() {
        return "ShippingRequest{" + "item=" + item + ", shippingAddress=" + shippingAddress + ", shippingMethod=" + shippingMethod + ", insuranceAmount=" + insuranceAmount + '}';
    }
  
  
}
