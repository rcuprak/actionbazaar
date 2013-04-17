package com.actionbazaar.listing08;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
	@Id @Column(name="CAT_NAME") private String name;
	@Id @Column(name="CAT_DATE") private java.util.Date createdDate;
	// ...
}

