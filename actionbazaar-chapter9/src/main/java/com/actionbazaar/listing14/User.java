package com.actionbazaar.listing14;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "USER_SEQUENCE_GENERATOR", sequenceName = "USER_SEQUENCE", initialValue = 1, allocationSize = 10)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQUENCE_GENERATOR")
	@Column(name = "USER_ID")
	protected Long userId;
}
