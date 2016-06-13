package com.actionbazaar.listing10;

import java.sql.Date;

import javax.persistence.Embeddable;

@Embeddable
public class CategoryId {
	String name;
	Date createDate;

	public CategoryId() {}

	public boolean equals(Object other) {
	  if (other instanceof CategoryId) {
	    final CategoryId otherCategoryKey = (CategoryId) other;
	    return (otherCategoryKey.name.equals(name)
                &&
                otherCategoryKey.createDate.equals(createDate));
	  }
	  return false;
	}

	public int hashCode() {
	  return super.hashCode();
	}

	public String getName() {
		return name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	// ...
}

