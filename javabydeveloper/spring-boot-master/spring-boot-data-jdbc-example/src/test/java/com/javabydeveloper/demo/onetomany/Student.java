package com.javabydeveloper.demo.onetomany;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data // lombok
public class Student {

	@Id
	private Long id;

	private String contactNo;
	private String firstName;
	private String lastName;

}
