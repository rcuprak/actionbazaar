package com.actionbazaar.listing07;

import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User {
 	@ElementCollection
	@CollectionTable(name="PHONE_NUMBERS",
          joinColumns=@JoinColumn(name="USER_ID"))
	@Column(name="NUMBER")
	private Collection<String> telephoneNumbers;
//...
}

