package com.javabydeveloper.demo.template;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.javabydeveloper.model.User;
import com.javabydeveloper.util.UserType;

public class UserResultSetExtracter implements ResultSetExtractor<Map<UserType, List<User>>> {

	@Override
	public Map<UserType, List<User>> extractData(ResultSet rs) throws SQLException, DataAccessException {
		return extractUsers(rs);
	}
	
	private Map<UserType, List<User>> extractUsers(ResultSet rs) throws SQLException {
		Map<UserType, List<User>> users = new HashMap<UserType, List<User>>();
		List<User> studentlist = new ArrayList<User>();
		List<User> employeeList = new ArrayList<User>();
		while (rs.next()) {
			if(rs.getString("USERTYPE").equals(UserType.STUDENT.toString()))
				studentlist.add(extractUser(rs));
			else
				employeeList.add(extractUser(rs));
		}
		users.put(UserType.STUDENT, studentlist);
		users.put(UserType.EMPLOYEE, employeeList);
		return users;
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("ID"));
		user.setUserName(rs.getString("USERNAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setCreatedTime(rs.getDate("CREATEDTIME"));
		user.setUpdatedTime(rs.getDate("UPDATEDTIME"));
		user.setUserType(UserType.valueOf(rs.getString("USERTYPE")));
		user.setDateofBirth(rs.getDate("DATEOFBIRTH"));

		return user;
	}
}
