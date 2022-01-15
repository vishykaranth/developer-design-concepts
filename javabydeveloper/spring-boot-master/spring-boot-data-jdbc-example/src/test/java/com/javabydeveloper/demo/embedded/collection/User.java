package com.javabydeveloper.demo.embedded.collection;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

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

	@MappedCollection(idColumn = "USER_ID")
	private Set<ContactAddress> address;

	@MappedCollection(idColumn = "ID", keyColumn = "CONTACT_NO")
	private List<Contact> contacts;

	private UserType userType;

}
