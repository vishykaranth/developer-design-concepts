package com.javabydeveloper.demo.onetoone;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table("USER_CREDENTIALS")
@Data // lombok
public class Credentials {

	@Id
	private Long credsId;
	private String userName;
	private String password;

}
