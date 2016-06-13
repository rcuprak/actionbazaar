package com.actionbazaar.listing23;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="BIDDERS")
@DiscriminatorValue(value = "B")
@PrimaryKeyJoinColumn(name="USER_ID")
public class Bidder extends User {
}
