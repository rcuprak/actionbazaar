package com.actionbazaar.listing02;

import javax.persistence.Entity;

@Entity
public abstract class User {
	// ...
	String userId;
	String username;
	String email;
	byte[] picture;
	// ...
}

