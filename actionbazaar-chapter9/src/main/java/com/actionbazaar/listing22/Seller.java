package com.actionbazaar.listing22;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value = "S")
public class Seller extends User {
	//...the rest of seller omitted for brevity

	  @Transient
	  private double creditWorth;

	  @Column(name="CREDIT_WORTH")
	  @Access(AccessType.PROPERTY)
	  public double getCreditWorth() { return creditWorth; }
	  public void setCreditWorth(double cw) {
	    creditWorth = (cw <= 0) ? 50.0 : cw;
	  }
}
