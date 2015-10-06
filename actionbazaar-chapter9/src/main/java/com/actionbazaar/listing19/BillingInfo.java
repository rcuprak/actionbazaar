package com.actionbazaar.listing19;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BillingInfo {
  @Id
  protected Long billingId;
  protected String creditCardType;
  // ...
  @OneToOne(mappedBy="billingInfo", optional=false)
  protected User user;
}

