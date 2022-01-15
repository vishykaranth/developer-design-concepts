package com.javabydeveloper.demo.collection.map;

import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Table("CONTACTS")
@Data @AllArgsConstructor
public class Contact {

	private String contactNo;
}
