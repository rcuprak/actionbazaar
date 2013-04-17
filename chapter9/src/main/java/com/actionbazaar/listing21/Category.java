package com.actionbazaar.listing21;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Category {
	@Id
	protected Long categoryId;
	protected String name;
	@ManyToMany
	protected Set<Item> items;
}
