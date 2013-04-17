package com.actionbazaar.listing11;

import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.actionbazaar.listing10.CategoryId;

@Entity
public class Category {
	@EmbeddedId
private CategoryId categoryId;
	// ...

	public String getName() {
	  return categoryId.getName();
	}
	public Date getCreateDate() {
	  return categoryId.getCreateDate();
	}
}

