package com.javabydeveloper.model;

import java.util.Date;

import com.javabydeveloper.util.UserType;

import lombok.Data;

@Data // lomok
public class User {

	private Long id;
	private String userName;
	private String password;
	private Date createdTime;
	private Date updatedTime;
	private Date dateofBirth;
	private UserType userType; // Enum Type
}
