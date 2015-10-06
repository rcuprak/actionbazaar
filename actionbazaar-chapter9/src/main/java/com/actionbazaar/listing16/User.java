package com.actionbazaar.listing16;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(name = "USER_TABLE_GENERATOR", table = "SEQUENCE_GENERATOR_TABLE", pkColumnName = "SEQUENCE_NAME", valueColumnName = "SEQUENCE_VALUE", pkColumnValue = "USER_SEQUENCE")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_TABLE_GENERATOR")
	@Column(name = "USER_ID")
	protected Long userId;
}
