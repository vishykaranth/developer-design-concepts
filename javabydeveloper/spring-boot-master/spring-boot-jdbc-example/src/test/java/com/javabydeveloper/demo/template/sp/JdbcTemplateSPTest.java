package com.javabydeveloper.demo.template.sp;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;

import com.javabydeveloper.base.BaseTest;
import com.javabydeveloper.model.User;
import com.javabydeveloper.template.UserJdbcTemplate;
import com.javabydeveloper.util.UserType;

@SpringBootTest
@SqlGroup({ @Sql(scripts = "/basic_mapping.sql"),
		@Sql(scripts = "/stored_procedures.sql", config = @SqlConfig(encoding = "utf-8", separator = "$")) })
@TestMethodOrder(value = OrderAnnotation.class) // to run tests in order
public class JdbcTemplateSPTest extends BaseTest {

	@Autowired
	private UserJdbcTemplate jdbcTemplate;

	// MySql stored procedure test
	// @Disabled("Enable to run On MySql only")
	@Test
	@Order(1)
	@DisplayName("Stored Procedure Test ")
	void searchUserTest() {

		Map<String, Object> out = jdbcTemplate.searchUserByName("pet");

		List<User> users = mapUsers(out, 1);
		System.err.println(users);
		assertTrue(users.size() == 4);
	}

	// @Disabled("Enable to run On MySql only")
	@Test
	@Order(2)
	@DisplayName("SimpleJdbcCall - Stored Procedure Test ")
	void countSearchUserTest() {

		Map<String, Object> out = jdbcTemplate.searchUsersWithCount("USERNAME", "DESC", 5, 0);

		List<User> users = mapUsers(out, 1);

		// out param
		int total = (Integer) out.get("total");

		System.err.println(users);
		assertTrue(users.size() == 5);
		assertTrue(total == 12);
	}

	// @Disabled("Enable to run On MySql only")
	@Test
	@Order(3)
	@DisplayName("Stored Procedure Multiple Result Sets Test")
	void searchWeakUserTest() {

		Map<String, Object> out = jdbcTemplate.searchWeakUsers(UserType.EMPLOYEE.toString());
		System.err.println(out);
		List<User> usersHasWeakUsername = mapUsers(out, 1);
		List<User> usersHasWeakPassword = mapUsers(out, 2);
		// out param
		int total = (Integer) out.get("type_total");

		// Last updated statements count
		int updatedCount = (Integer) out.get("#update-count-1");

		System.out.println(total);
	}

	@Test
	@Order(4)
	@DisplayName("Stored Function Test")
	void userHasWeakPasswordTest() {

		boolean userHasWeakPass = jdbcTemplate.hasUserStrongPassword(Long.valueOf(1));

		assertTrue(!userHasWeakPass);
	}

	public List<User> mapUsers(Map<String, Object> out, int resultSetPosition) {
		List<User> users = new ArrayList<User>();
		List<Map<String, Object>> results = (List<Map<String, Object>>) out.get("#result-set-" + resultSetPosition);

		results.forEach(u -> {
			User user = new User();
			user.setId((Long) u.get("Id"));
			user.setUserName((String) u.get("USERNAME"));
			user.setPassword((String) u.get("PASSWORD"));
			user.setCreatedTime((Date) u.get("CREATEDTIME"));
			if (u.get("UPDATEDTIME") != null)
				user.setCreatedTime((Date) u.get("UPDATEDTIME"));
			user.setUserType(UserType.valueOf((String) u.get("USERTYPE")));
			user.setDateofBirth((Date) u.get("DATEOFBIRTH"));

			users.add(user);
		});

		return users;
	}
}
