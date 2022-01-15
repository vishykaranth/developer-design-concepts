package com.javabydeveloper.demo.template;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.CachedRowSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.jdbc.Sql;

import com.javabydeveloper.base.BaseTest;
import com.javabydeveloper.util.UserType;

@SpringBootTest
@Sql(scripts = "/basic_mapping.sql") // to created DB tables and init sample DB data
@TestMethodOrder(value = OrderAnnotation.class) // to run tests in order
public class JdbcTemplateQueryForRowSetTest extends BaseTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Order(1)
	@Test
	@DisplayName("find-weak-user-names-test")
	void findWeakUsersTest() throws InvalidResultSetAccessException, SQLException {
		
		String sql = "SELECT USERNAME FROM USER where LENGTH(USERNAME) < 7";
		
		ResultSetWrappingSqlRowSet rowset = (ResultSetWrappingSqlRowSet) jdbcTemplate.queryForRowSet(sql);
		CachedRowSet crs = (CachedRowSet) rowset.getResultSet();
		
		List<String> users = new ArrayList<String>();	
		
		while(crs.next()){
			users.add(rowset.getString("USERNAME"));
		}
		
		System.out.println(users);
	}
	
	@Order(2)
	@DisplayName("find-users-age-test")
	@ParameterizedTest
	@EnumSource(names = {"EMPLOYEE"})
	void findUsersAgeTest(UserType userType) {
		
		String sql = "SELECT USERNAME, TIMESTAMPDIFF(YEAR, DATEOFBIRTH, CURDATE()) as AGE FROM USER WHERE USERTYPE=?";
		
		SqlRowSet rowset = jdbcTemplate.queryForRowSet(sql, new Object[]{userType}, new int[]{Types.VARCHAR});
		
		List<Map<String, Integer>> users = new ArrayList<Map<String, Integer>>();
		
		while(rowset.next()){
			users.add(Map.of(rowset.getString("USERNAME"), rowset.getInt("Age")));
		}
		
		System.out.println(users);
	}
}
