package com.javabydeveloper.template;

import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.javabydeveloper.model.User;

@Repository
public class UserSimpleJdbcInsert {

	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcInsert simpleJdbcInsert;

	@Autowired
	public UserSimpleJdbcInsert(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
	}

	public Long save(User user) {
		String insertSql = "INSERT INTO `USER` (USERNAME, PASSWORD, CREATEDTIME, UPDATEDTIME, USERTYPE, DATEOFBIRTH)"
				+ " VALUES(?,?,?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {

			PreparedStatement ps = connection.prepareStatement(insertSql, new String[] { "ID" });
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setDate(3, new java.sql.Date(user.getCreatedTime().getTime()));
			ps.setDate(4, user.getUpdatedTime() == null ? null : new java.sql.Date(user.getUpdatedTime().getTime()));
			ps.setString(5, user.getUserType().toString());
			ps.setDate(6, new java.sql.Date(user.getDateofBirth().getTime()));

			return ps;

		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

	public Long saveWithSimpleJdbcInsert(User user) {
		
		simpleJdbcInsert.withTableName("USER").usingGeneratedKeyColumns("ID");

		Number id = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(user));
		
		return id.longValue();
	}
}
