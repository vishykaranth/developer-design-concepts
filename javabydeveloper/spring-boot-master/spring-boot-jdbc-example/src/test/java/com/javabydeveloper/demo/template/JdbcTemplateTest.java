package com.javabydeveloper.demo.template;

import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.jdbc.Sql;

import com.google.common.collect.Ordering;
import com.javabydeveloper.base.BaseTest;
import com.javabydeveloper.model.User;
import com.javabydeveloper.template.UserJdbcTemplate;
import com.javabydeveloper.util.UserType;

@SpringBootTest
@Sql(scripts = "/basic_mapping.sql") // to created DB tables and init sample DB data
@TestMethodOrder(value = OrderAnnotation.class) // to run tests in order
public class JdbcTemplateTest extends BaseTest {

	@Autowired
	private UserJdbcTemplate userRepository;


	@Test
	@DisplayName("Create-User-Test ")
	@Order(1)
	void createUserTest() {

		//int created = userRepository.save(getUser());
		
		Optional<User> user = userRepository.findByUserName("Karolina");
		user.ifPresent(System.out::println);
		
		//Optional user4 = userRepository.findByUserName("AAAA");
		

		//assertTrue(created == 1);
	}

	@Disabled
	@Test
	@DisplayName("Update-User-Test ")
	@Order(2)
	void updateUserTest() {

		// Read all users
		List<User> allUsers = (List<User>) userRepository.findAll();

		allUsers.forEach(user -> {
			user.setPassword("ABC123abc#");
			int updated = userRepository.update(user);

			assertTrue(updated == 1);
		});

	}

	@Disabled
	// Gets all of user data - Test on small sample data
	@Test
	@DisplayName("Sorting-By-UserName-Test")
	@Order(3)
	void sortByUserName() {

		// By user name in descending order
		Sort sort = Sort.by(Direction.fromString("DESC"), "USERNAME");

		// All Users
		List<User> users = (List<User>) userRepository.findAll();

		// Sorted Users
		List<User> sortedUsers = userRepository.findAll(sort);

		List<User> userList = users.stream().sorted(Comparator.comparing(User::getUserName).reversed())
				.collect(Collectors.toList());

		Ordering<User> expectedOrder = Ordering.explicit(userList);

		assertTrue(expectedOrder.isOrdered(sortedUsers));

	}

	@Disabled
	@Test
	@DisplayName("Results-By-Page-And-Size-Test")
	@Order(4)
	void getByPageAndSize() {

		// total 12 users in sample data, set 5 users per page - total 3 pages
		PageRequest pageable = PageRequest.of(0, 5);

		// All users
		List<User> users = (List<User>) userRepository.findAll();

		// paged users - each page should have 5 users
		Page<User> pagedUsers = userRepository.findAll(pageable);

		assertTrue(pagedUsers.getTotalPages() == 3);
		assertTrue(pagedUsers.getContent().equals(users.subList(0, 5)));
	}

	@Disabled
	@Test
	@DisplayName("Results-By-Page-Size-And-SortBy-Test")
	@Order(5)
	void getByPageAndSizeSortByUserName() {

		PageRequest pageable = PageRequest.of(0, 5, Direction.fromString("DESC"), "USERNAME");
		System.err.println(pageable.getSort().toList().get(0));
		// All Users
		List<User> users = (List<User>) userRepository.findAll();

		Page<User> pagedUsers = userRepository.findAll(pageable);

		List<User> usersList = users.stream().sorted(Comparator.comparing(User::getUserName).reversed())
				.collect(Collectors.toList()).subList(0, 5);

		Ordering<User> expectedOrder = Ordering.explicit(usersList);

		assertTrue(expectedOrder.isOrdered(pagedUsers.getContent()));

		assertTrue(pagedUsers.getTotalPages() == 3);
		assertTrue(pagedUsers.getContent().equals(usersList));
	}

	@Disabled
	@Test
	@DisplayName("Delete-User-Test ")
	@Order(6)
	void deleteUserTest() {

		// Read all users
		List<User> allUsers = (List<User>) userRepository.findAll();

		allUsers.forEach(user -> {
			userRepository.delete(user);
			//assertTrue(userRepository.findById(user.getId()));
		});

	}

	private User getUser() {

		User user = new User();
		user.setUserType(UserType.STUDENT);
		user.setUserName("PhilipX");
		user.setPassword("ABC123abc*");
		user.setDateofBirth(new Date());
		user.setCreatedTime(new Date());

		return user;
	}
}
