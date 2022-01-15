package com.javabydeveloper.template.callback;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.javabydeveloper.model.User;

public class UserBatchPreparedStatementSetter implements BatchPreparedStatementSetter{

	private List<User> users;
	
	public UserBatchPreparedStatementSetter(List<User> users) {
		super();
		this.users = users;
	}

	@Override
	public void setValues(PreparedStatement ps, int i) {
		
		try {
			User user = users.get(i);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setDate(3, new java.sql.Date(user.getCreatedTime().getTime()));
			ps.setDate(4, user.getUpdatedTime() == null ? null : new java.sql.Date(user.getUpdatedTime().getTime()));
			ps.setString(5, user.getUserType().toString());
			ps.setDate(6, new java.sql.Date(user.getDateofBirth().getTime()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getBatchSize() {
		return users.size();
	}
}
