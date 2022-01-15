package com.javabydeveloper.demo.template.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.javabydeveloper.model.User;
import com.javabydeveloper.template.UserBatchUpdateService;
import com.javabydeveloper.template.UserJdbcTemplate;
import com.javabydeveloper.util.CSVUtils;

@Disabled("Enable on Mysql")
@SpringBootTest
@TestMethodOrder(value = OrderAnnotation.class)
public class UserBatchUpdateTest {

	@Autowired
	private UserBatchUpdateService batchService;

	@Autowired
	private UserJdbcTemplate jdbcTemplate;

	//@Disabled
	@Sql(statements = { "TRUNCATE TABLE USER;" })
	@Test
	@DisplayName("Batch-Insert-Test")
	@Order(1)
	void batchInsertTest() throws InterruptedException, ExecutionException {
		// ex: get users from csv file
		List<User> users = new ArrayList<User>();

		for (int i = 0; i <= 100000; i++) {
			List<User> tmp = CSVUtils.getAllUsersData();

			for (User u : tmp) {
				u.setUserName(u.getUserName() + "-" + i);
				users.add(u);
			}
		}

		batchService.batchInsert(users);

		// List<User> findUsers = jdbcTemplate.findAll();
		// findUsers.forEach(System.err::println);
	}

	//@Disabled
	@Test
	@DisplayName("Batch-Update-Test")
	@Order(2)
	void batchUpdateTest() throws InterruptedException, ExecutionException {
		// ex: get users from csv file
		List<User> users = jdbcTemplate.findAll();

		batchService.batchUpdate(users);

		// List<User> findUsers = jdbcTemplate.findAll();
		// findUsers.forEach(System.err::println);
	}

	@Disabled
	@Sql(statements = { "TRUNCATE TABLE USER;" })
	@Test
	@DisplayName("Batch-Insert-Async-Test")
	@Order(3)
	void batchInsertAsyncTest() throws InterruptedException, ExecutionException {
		// ex: get users from csv file
		List<User> users = new ArrayList<User>();

		for (int i = 0; i <= 100000; i++) {
			List<User> tmp = CSVUtils.getAllUsersData();

			for (User u : tmp) {
				u.setUserName(u.getUserName() + "-" + i);
				users.add(u);
			}
		}

		batchService.batchInsertAsync(users);

		// List<User> findUsers = jdbcTemplate.findAll();
		// findUsers.forEach(System.err::println);
	}
	
	@Disabled
	@Test
	@DisplayName("Batch-Update-Async-Test")
	@Order(4)
	void batchUpdateStaticTest() throws InterruptedException, ExecutionException {
		
		List<User> users = jdbcTemplate.findAll();
		
		final AtomicInteger sublists = new AtomicInteger();

		users.stream()
				.collect(Collectors.groupingBy(t -> sublists.getAndIncrement() / 100000))
				.values().forEach(usl -> {
					try {
						batchService.batchUpdateStatic(usl);
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
				});
	}
}
