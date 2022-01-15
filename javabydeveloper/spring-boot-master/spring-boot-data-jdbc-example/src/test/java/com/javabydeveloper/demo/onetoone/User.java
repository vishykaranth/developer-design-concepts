package com.javabydeveloper.demo.onetoone;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;

import com.javabydeveloper.util.UserType;

import lombok.Data;

@Data
public class User {

	@Id
	private Long id;
	private Date createdTime;
	private Date updatedTime;
	@Column("DOB")
	private Date dateofBirth;
	private UserType userType;

	@MappedCollection(idColumn = "CREDS_ID")
	private Credentials credentials;

}
