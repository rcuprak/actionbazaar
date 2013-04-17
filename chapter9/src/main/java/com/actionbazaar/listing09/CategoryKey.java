package com.actionbazaar.listing09;

import java.io.Serializable;
import java.sql.Date;

public class CategoryKey implements Serializable {
	private static final long serialVersionUID = 1775396841L;
	String name;
	Date createDate;

	public CategoryKey() {
	}

	public boolean equals(Object other) {
		if (other instanceof CategoryKey) {
			final CategoryKey otherCategoryKey = (CategoryKey) other;
			return (otherCategoryKey.name.equals(name) && otherCategoryKey.createDate
					.equals(createDate));
		}
		return false;
	}

	public int hashCode() {
		return super.hashCode();
	}
}
