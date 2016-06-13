package com.actionbazaar.listing06;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {
	private Collection<String> telephoneNumbers;
	// ...
}
