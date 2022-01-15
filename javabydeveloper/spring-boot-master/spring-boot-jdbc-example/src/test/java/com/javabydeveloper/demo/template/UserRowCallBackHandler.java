package com.javabydeveloper.demo.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.javabydeveloper.model.User;
import com.javabydeveloper.util.UserType;

public class UserRowCallBackHandler implements RowCallbackHandler {

	private ObjectMapper objectMapper = new ObjectMapper();
	private List<User> users;

	public UserRowCallBackHandler() {
		users = new ArrayList<User>();
	}

	@Override
	public void processRow(ResultSet rs) throws SQLException {

		User user = extractUser(rs);
		try {
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.writeValue(getUsersJosonFile(user.getId()), user);
			users.add(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private File getUsersJosonFile(Long id) {
		File file = null;
		try {
			File testFile = ResourceUtils.getFile("classpath:jsondata/test.txt");
			file = new File(testFile.getPath().replaceAll("test.txt", "user-" + id + ".json"));
			if (!file.exists())
				file.createNewFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	public List<User> getUsers() {
		return this.users;
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
