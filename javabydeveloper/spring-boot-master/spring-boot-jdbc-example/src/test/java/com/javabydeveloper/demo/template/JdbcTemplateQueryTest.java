package com.javabydeveloper.demo.template;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import com.javabydeveloper.base.BaseTest;
import com.javabydeveloper.model.User;
import com.javabydeveloper.util.UserType;

@SpringBootTest
@Sql(scripts = "/basic_mapping.sql") // to created DB tables and init sample DB data
@TestMethodOrder(value = OrderAnnotation.class) // to run tests in order
public class JdbcTemplateQueryTest extends BaseTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	@DisplayName("find-all-users-test")
	@Order(1)
	void findAllUsersTest() {

		String sql = "SELECT * FROM USER";
		
		List<User> users = jdbcTemplate.query(sql, new UserMapper());

		assertTrue(users.size() == 12);
		
		users.forEach(System.err::println); // just to print  in red color
	}

	@Order(2)
	@DisplayName("find-users-by-name-test")
	@ParameterizedTest
	@ValueSource(strings = {"%Peter%"})
	void findUsersByNameTest(String name) {

		String sql = "SELECT * FROM USER where USERNAME LIKE ?";

		Map<UserType, List<User>> users = jdbcTemplate.query(sql, new Object[] {name}, new UserResultSetExtracter());

		assertTrue(users.get(UserType.STUDENT).size() == 2);
		assertTrue(users.get(UserType.EMPLOYEE).size() == 2);

	}
	
	@Order(3)
	@DisplayName("find-users-by-type-test")
	@ParameterizedTest
	@EnumSource(value = UserType.class)
	void findUsersByTypeTest(UserType userType) {

		String sql = "SELECT * FROM USER WHERE USERTYPE = ?";

		List<User> users = jdbcTemplate.query(sql,
				new Object[] { userType },
				new int[] { Types.VARCHAR },
				new UserMapper());

		if (userType.equals(UserType.STUDENT))
			assertTrue(users.size() == 4);
		else
			assertTrue(users.size() == 8);
		
	  System.err.println(users);
	}


	@Test
	@DisplayName("find-users-by-weak-username-test")
	@Order(4)
	void findUsersHasWeakUserNameTest() {

		String sql = "SELECT * FROM USER where LENGTH(USERNAME) < 6";

		UserRowCallBackHandler callbackHandler = new UserRowCallBackHandler();
		jdbcTemplate.query(sql, callbackHandler);

		assertTrue(callbackHandler.getUsers().size() == 1);
	}
	
	@Order(5)
	@DisplayName("findUsersByNameTest")
	@ParameterizedTest
	@ValueSource(strings = {"%Peter%"})
	void findByNameUsersTest(String name) {

		String sql = "SELECT * FROM USER where USERNAME LIKE ?";

		List<User> users = jdbcTemplate.query(new UserPreparedStatementCreator(sql, name), new UserMapper());

		assertTrue(users.size() == 4);
	
	}
	
	@Order(6)
	@DisplayName("findUsersByNameAndTypeTest")
	@ParameterizedTest
	@MethodSource("userNameAndTypeProvider")
	void findByNameAndTyepTest(String name, UserType type) {

		String sql = "SELECT * FROM USER where USERNAME LIKE ? AND USERTYPE = ?";

		List<User> users = jdbcTemplate.query(sql, new UserPreparedStatementSetter(name, type), new UserMapper());
		
		assertTrue(users.size() == 2);
	}
	
	static Stream<Arguments> userNameAndTypeProvider(){
		return Stream.of(
	            arguments("Peter%", UserType.STUDENT),
	            arguments("%King%", UserType.EMPLOYEE)
	        );
	}
}
