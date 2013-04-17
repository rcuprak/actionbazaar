package com.actionbazaar.listing18;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {
	@Id
	protected String userId;
	protected String email;
	@OneToOne
	protected BillingInfo billingInfo;
}
