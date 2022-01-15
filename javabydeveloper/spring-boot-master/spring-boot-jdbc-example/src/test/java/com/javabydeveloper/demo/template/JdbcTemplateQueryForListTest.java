package com.javabydeveloper.demo.template;

import static org.junit.Assert.assertTrue;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import com.javabydeveloper.base.BaseTest;
import com.javabydeveloper.util.UserType;

@SpringBootTest
@Sql(scripts = "/basic_mapping.sql") // to created DB tables and init sample DB data
@TestMethodOrder(value = OrderAnnotation.class) // to run tests in order
public class JdbcTemplateQueryForListTest extends BaseTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Order(1)
	@Test
	@DisplayName("find-by-id-test")
	void findWeakUsersTest() {
		
		String sql = "SELECT USERNAME FROM USER where LENGTH(USERNAME) < 7";
		
		List<String> users = jdbcTemplate.queryForList(sql, String.class);
		assertTrue(users.size() == 5);
	}
	
	@Order(2)
	@Test
	@DisplayName("find-users-test")
	void findUsersTest() {
		
		String sql = "SELECT ID, USERNAME, IF(PASSWORD REGEXP '[0-9]', 'true', 'false') as HAS_NUMERIC_PASSWORD FROM USER";
		
		List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);
		
		users.forEach( rowMap -> {
			Long id = (Long) rowMap.get("ID");
			String userName = (String) rowMap.get("USERNAME");
			boolean hasNumericPass = Boolean.valueOf(rowMap.get("HAS_NUMERIC_PASSWORD").toString());

			System.out.println(id +"-"+userName+"-"+hasNumericPass);
		});
	}

	@Order(3)
	@DisplayName("find-users-age-test")
	@ParameterizedTest
	@EnumSource(names = {"EMPLOYEE"})
	void findUsersAgeTest(UserType userType) {
		
		// TIMESTAMPDIFF(YEAR, DATEOFBIRTH, CURDATE()) - gets dates differnce in years
		String sql = "SELECT USERNAME, TIMESTAMPDIFF(YEAR, DATEOFBIRTH, CURDATE()) as AGE FROM USER WHERE USERTYPE=?";
		
		List<Map<String, Object>> users = jdbcTemplate.queryForList(sql, new Object[]{userType}, new int[]{Types.VARCHAR});
		
		users.forEach( rowMap -> {
			String userName = (String) rowMap.get("USERNAME");
			int age = ((Long) rowMap.get("AGE")).intValue();

			System.out.println(userName+"-"+age);
		});
	}
}
