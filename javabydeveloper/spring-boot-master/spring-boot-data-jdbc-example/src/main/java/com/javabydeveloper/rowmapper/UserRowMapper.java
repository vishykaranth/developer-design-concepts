package com.javabydeveloper.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.javabydeveloper.model.User;
import com.javabydeveloper.util.UserType;

public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		user.setId(rs.getLong("ID"));
		user.setUserName(rs.getString("USER_NAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setEmail(rs.getString("EMAIL"));
		user.setCreatedTime(rs.getDate("CREATED_TIME"));
		user.setUpdatedTime(rs.getDate("UPDATED_TIME"));
		user.setUserType(UserType.valueOf(rs.getString("USER_TYPE")));
		user.setDateofBirth(rs.getDate("DOB"));

		return user;
	}
}