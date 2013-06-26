package com.actionbazaar.listing23;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="SELLERS")
@DiscriminatorValue(value = "S")
@PrimaryKeyJoinColumn(name="USER_ID")
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
