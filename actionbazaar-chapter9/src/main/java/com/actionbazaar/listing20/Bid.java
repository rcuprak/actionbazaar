package com.actionbazaar.listing20;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bid {
  @Id
  protected Long bidId;
  protected Double amount;
  protected Date timestamp;
  @ManyToOne
  protected Item item;
}

