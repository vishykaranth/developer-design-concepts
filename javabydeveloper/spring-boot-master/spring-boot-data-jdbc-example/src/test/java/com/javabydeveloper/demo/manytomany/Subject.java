package com.javabydeveloper.demo.manytomany;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data // lombok
public class Subject {

	@Id
	private Long subjectId;

	private String subjectDesc;
	private String subjectName;

}
