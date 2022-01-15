package com.javabydeveloper.demo.collection.map;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import com.javabydeveloper.util.ContactType;
import com.javabydeveloper.util.UserType;

import lombok.Data;

@Data
public class User {

	@Id
	private Long id;

	private String userName;
	private String password;
	private Date createdTime;
	private Date updatedTime;

	@MappedCollection(idColumn = "ID", keyColumn = "CONTACT_TYPE")
	private Map<ContactType, Contact> contacts;

	private UserType userType;

}
