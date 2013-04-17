package com.actionbazaar.listing17;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class User {
	@Id
	@Column(name = "USER_ID")
	protected String userId;

	@PrePersist
	public void generatePrimaryKey() {
		userId = UUID.randomUUID().toString();
	}
}
