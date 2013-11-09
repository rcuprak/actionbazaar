/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.actionbazaar.listing02;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author <a href="mailto:mjremijan@yahoo.com">Michael Remijan</a>
 */
@Entity
@Table(name="SHIPPING_REQUEST")
public class TurtleShippingRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ITEM_ID")
    private String item;
    @Column(name = "SHIP_ADDRESS")    
    private String shippingAddress;
    @Column(name = "SHIP_METHOD")
    private String shippingMethod;
    @Column(name = "SHIP_INSURANCE_DOLLARS")
    private double insuranceAmount;
  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  
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
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TurtleShippingRequest other = (TurtleShippingRequest) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TurtleShippingRequest{" + "id=" + id + ", item=" + item + ", shippingAddress=" + shippingAddress + ", shippingMethod=" + shippingMethod + ", insuranceAmount=" + insuranceAmount + '}';
    }
}
