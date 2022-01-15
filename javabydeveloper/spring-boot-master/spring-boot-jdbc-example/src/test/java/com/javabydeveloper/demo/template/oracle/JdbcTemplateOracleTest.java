package com.javabydeveloper.demo.template.oracle;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.javabydeveloper.base.BaseTest;
import com.javabydeveloper.demo.template.UserMapper;
import com.javabydeveloper.model.User;
import com.javabydeveloper.util.UserType;

@Disabled("Enable only on Oracle")
@SpringBootTest
//Create DB tables and init sample DB data
@Sql(scripts = { "/oracle/drop_schema.sql", "/oracle/basic_mapping_oracle.sql" }, config = @SqlConfig(separator = "$"))
@TestMethodOrder(value = OrderAnnotation.class) // to run tests in order
public class JdbcTemplateOracleTest extends BaseTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	@DisplayName("Create-User-Test ")
	@Order(1)
	void createUserTest() {

		// Cause 1
		List<User> users1 = jdbcTemplate.query(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				PreparedStatement ps = con.prepareStatement("SELECT * FROM USER_ where USERNAME LIKE ? AND USERTYPE=?");
				ps.setString(1, "PeterM");
				ps.setString(2, UserType.STUDENT.toString());

				return ps;
			}
		}, new UserMapper());

		System.err.println(users1);
		// assertTrue(users1.size() == 1);

		// Cause 2
		String sql = "SELECT ID, USERNAME, PASSWORD, CREATEDTIME FROM USER_ where USERNAME LIKE ?";
		List<User> users2 = jdbcTemplate.query(sql, new Object[] { "PeterM" }, new ResultSetExtractor<List<User>>() {

			@Override
			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<User> values = new ArrayList<User>();
				while (rs.next()) {
					User u = new User();
					u.setId(rs.getLong("ID"));
					u.setUserName(rs.getString("USERNAME"));
					u.setPassword(rs.getString("PASSWORD"));
					u.setCreatedTime(rs.getDate("CREATEDTIME"));
					
					values.add(u);
				}
				return values;
			}
		});

		System.err.println(users2);

		// Cause 3
		User user = getUser();
		int created = jdbcTemplate.update(
				"INSERT INTO USER_ (USERNAME, PASSWORD, CREATEDTIME, UPDATEDTIME, USERTYPE, DATEOFBIRTH)"
						+ " VALUES(?,?,?,?,?,?)",
				user.getUserName(),
				user.getPassword(),
				user.getCreatedTime(),
				user.getUpdatedTime(),
				user.getUserType().toString(), user.getDateofBirth());

		assertTrue(created == 1);

		System.out.println(jdbcTemplate.query("SELECT * FROM USER_", new UserMapper()));
	}

	private User getUser() {

		User user = new User();
		user.setUserType(UserType.STUDENT);
		user.setUserName("MullerX");
		user.setPassword("ABC123abc*");
		user.setDateofBirth(new Date());
		user.setCreatedTime(new Date());

		return user;
	}
}
