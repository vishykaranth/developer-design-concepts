package com.javabydeveloper.demo.template;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import com.javabydeveloper.base.BaseTest;
import com.javabydeveloper.util.UserType;

@SpringBootTest
@Sql(scripts = "/basic_mapping.sql") // to created DB tables and init sample DB data
@TestMethodOrder(value = OrderAnnotation.class) // to run tests in order
public class JdbcTemplateQueryForMapTest extends BaseTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Order(1)
	@Test
	@DisplayName("find-first-user-test")
	void findFirstUserAgeTest() {
		
		// TIMESTAMPDIFF(YEAR, DATEOFBIRTH, CURDATE()) - gets dates differnce in years just for test
		String sql = "SELECT USERNAME, TIMESTAMPDIFF(YEAR, DATEOFBIRTH, CURDATE()) as AGE FROM USER LIMIT 1";
		
		Map<String, Object> rowMap = jdbcTemplate.queryForMap(sql);

		String userName = (String) rowMap.get("USERNAME");
		int age = ((Long) rowMap.get("AGE")).intValue();
		
		System.out.println(userName+"-"+age);
	}
	
	@Disabled("enable on mysql db")
	@Order(2)
	@DisplayName("user-by-name")
	@ParameterizedTest
	@ValueSource(strings = {"PeterM"})
	void findUserByNameTest(String name) {
		
		String sql = "SELECT ID, USERNAME, IF(PASSWORD REGEXP '[0-9]', 'true', 'false') as HAS_NUMERIC_PASSWORD FROM USER WHERE USERNAME=?";
		
		Map<String, Object> rowMap = jdbcTemplate.queryForMap(sql, new Object[]{name});
		
		Long id = (Long) rowMap.get("ID");
		String userName = (String) rowMap.get("USERNAME");
		boolean hasNumericPass = Boolean.valueOf(rowMap.get("HAS_NUMERIC_PASSWORD").toString());

		System.out.println(id +"-"+userName+"-"+hasNumericPass);
	}
}
