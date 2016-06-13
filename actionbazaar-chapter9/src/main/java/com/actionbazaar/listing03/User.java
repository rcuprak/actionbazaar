package com.actionbazaar.listing03;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {
	@Column(name = "USER_ID")
	String userId;
	@Column(name = "USER_NAME")
	String username;
	String email;

	// ...Other getters and setters ommitted for brevity
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
