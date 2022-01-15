package com.javabydeveloper.demo.template;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementSetter;

import com.javabydeveloper.util.UserType;

public class UserPreparedStatementSetter implements PreparedStatementSetter {

	private String name;
	private UserType type;

	public UserPreparedStatementSetter(String name, UserType type) {
		super();
		this.name = name;
		this.type = type;
	}

	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		ps.setString(1, name);
		ps.setString(2, type.toString());
	}
}
