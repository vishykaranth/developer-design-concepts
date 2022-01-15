package com.javabydeveloper.demo.onetomany;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import lombok.Data;

@Data
public class Branch {

	@Id
	private int branchId;

	private String branchName;
	private String branchShortName;
	private String description;

	@MappedCollection(keyColumn = "BRANCH_ID", idColumn = "BRANCH_ID")
	private Set<Student> students;

}
