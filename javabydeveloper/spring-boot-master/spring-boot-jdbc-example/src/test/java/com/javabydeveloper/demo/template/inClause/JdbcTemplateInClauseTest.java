package com.javabydeveloper.demo.template.inClause;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import com.javabydeveloper.demo.template.UserMapper;
import com.javabydeveloper.model.User;

@SpringBootTest
@Sql(scripts = "/basic_mapping.sql") // to created DB tables and init sample DB data
@TestMethodOrder(value = OrderAnnotation.class) // to run tests in order
public class JdbcTemplateInClauseTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Test
	@DisplayName("Jdbctemplate-IN-Clause-Static-Query-Test")
	@Order(1)
	void jdbcTemplateInClauseStaticTest() {

		List<Integer> ids = List.of(1, 2, 3, 4, 5);

		String inParams = String.join(",", ids.stream().map(id -> id + "").collect(Collectors.toList()));

		List<User> users = jdbcTemplate.query(String.format("SELECT * FROM USER WHERE ID IN (%s)", inParams),
				new UserMapper());

		assertTrue(users.size() == 5);

		// users.forEach(System.err::println); // just to print in red color
	}

	@Test
	@DisplayName("Jdbctemplate-IN-Clause-Dynamic-Query-Test")
	@Order(2)
	void jdbcTemplateInClauseDynamicTest() {

		List<Integer> ids = List.of(1, 2, 3, 4, 5);

		String inParams = String.join(",", ids.stream().map(id -> "?").collect(Collectors.toList()));

		List<User> users = jdbcTemplate.query(String.format("SELECT * FROM USER WHERE ID IN (%s)", inParams),
				ids.toArray(), new UserMapper());

		assertTrue(users.size() == 5);

		// users.forEach(System.err::println); // just to print in red color
	}

	@Test
	@DisplayName("NamedParameterJdbctemplate-IN-Clause-Dynamic-Query-Test")
	@Order(3)
	void namedParameterJdbcTemplateInClauseDynamicTest() {

		List<String> names = List.of("PeterM", "Mike", "KingPeter", "PeterH", "Kelvin");

		MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
		inQueryParams.addValue("names", names);

		List<User> users = namedJdbcTemplate.query(
				"SELECT * FROM USER WHERE USERNAME IN (:names)", 
				inQueryParams,
				new UserMapper());

		assertTrue(users.size() == 5);

		// users.forEach(System.err::println); // just to print in red color
	}
	
	@Test
	@DisplayName("Multiple-IN-Clause-Dynamic-Query-Test")
	@Order(4)
	void multipleInCluseJdbcTemplateInClauseDynamicTest() {

		List<Object[]> id_types = List.of(new Object[]{1, "STUDENT"}, new Object[]{2, "EMPLOYEE"});

		MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
		inQueryParams.addValue("idAndTypes", id_types);

		List<User> users = namedJdbcTemplate.query(
				"SELECT * FROM USER WHERE (ID, USERTYPE) IN (:idAndTypes)", 
				inQueryParams,
				new UserMapper());

		assertTrue(users.size() == 2);

		//users.forEach(System.err::println); // just to print in red color
	}
	
	@Test
	@DisplayName("Jdbctemplate-IN-Clause-LargeValues-Test")
	@Order(5)
	void jdbcTemplateLargeInClauseParamsTest() {
		
		List<Integer> ids = List.of(1, 2, 3, 4, 5);
		jdbcTemplate.execute("CREATE temporary TABLE IF NOT EXISTS USER_ID_TMP (ID bigint NOT NULL)");
		
		List<Object[]> idList = new ArrayList<Object[]>();
		ids.forEach(id -> idList.add(new Object[] {id}));
		jdbcTemplate.batchUpdate("INSERT INTO USER_ID_TMP (ID) VALUES (?)",idList);

		List<User> users = jdbcTemplate.query("SELECT * FROM USER WHERE ID IN (SELECT ID FROM USER_ID_TMP)",
				 new UserMapper());

		assertTrue(users.size() == 5);
		
		jdbcTemplate.update("TRUNCATE USER_ID_TMP");

		//users.forEach(System.err::println); // just to print in red color
	}
}
