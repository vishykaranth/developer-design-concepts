package com.javabydeveloper.demo;

import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.data.domain.Sort.TypedSort;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.test.context.jdbc.Sql;

import com.google.common.collect.Ordering;
import com.javabydeveloper.base.BaseTest;
import com.javabydeveloper.model.User;
import com.javabydeveloper.repository.UserPaginationAndSortRepository;
import com.javabydeveloper.util.UserType;

//@EnableJdbcRepositories(namedQueriesLocation = "/named-queries.properties")
@SpringBootTest
@Sql(scripts = "/basic_mapping.sql") // to created DB tables and init sample DB data
@TestMethodOrder(value = OrderAnnotation.class) // to run tests in order
public class PagingAndSortingRepositoryTest extends BaseTest {

	@Autowired
	private UserPaginationAndSortRepository userRepository;

	// Gets all of user data - Test on small sample data
	@Test
	@DisplayName("Sorting-By-UserName-Test")
	@Order(1)
	void sortByUserName() {

		// By user name in descending order
		Sort sort = Sort.by(Direction.fromString("DESC"), "userName");

		// All Users
		List<User> users = (List<User>) userRepository.findAll();

		// Sorted Users
		List<User> sortedUsers = (List<User>) userRepository.findAll(sort);

		List<User> userList = users.stream().sorted(Comparator.comparing(User::getUserName).reversed())
				.collect(Collectors.toList());

		Ordering<User> expectedOrder = Ordering.explicit(userList);

		assertTrue(expectedOrder.isOrdered(sortedUsers));

	}

	@Test
	@DisplayName("Results-By-Page-And-Size-Test")
	@Order(2)
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

	@Test
	@DisplayName("Results-By-Page-Size-And-SortBy-Test")
	@Order(3)
	void getByPageAndSizeSortByUserName() {

		PageRequest pageable = PageRequest.of(0, 5, Direction.fromString("DESC"), "userName");

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
	
	@Test
	@DisplayName("Query-expression-Pageable-and-Sort-Test")
	@Order(4)
	void getByPageAndSizeSortByQueryExpr() {

		PageRequest pageable = PageRequest.of(0, 5, Direction.fromString("DESC"), "userName");
		List<User> pagedAndSortedUsers = userRepository.findByUserType(UserType.EMPLOYEE.toString(), pageable);
		
		// Defining sort
		//Sort sort = Sort.by("userName").ascending();
		
		// defining Type-safe Sort
		TypedSort<User> tSort = Sort.sort(User.class);
		List<User> sortedUsers = userRepository.findByUserType(UserType.EMPLOYEE.toString(), tSort.by("userName").ascending());
		
		System.err.println(pagedAndSortedUsers);
		System.err.println(sortedUsers);
	}
	
	//@Disabled("Enable on MySql")
	@Test
	@DisplayName("NamedQuery-Test")
	@Order(5)
	void namedQueryTest() {

		boolean isUserHasStrongPassword = userRepository.hasStrongPassword("PeterM");
		
		int passwordLength = userRepository.sampleNamedQuery(1L);
		
		String userName = userRepository.getUserNameById(1L);
		
		System.err.println("Password Length : "+passwordLength);
		System.err.println("isUserHasStrongPassword : "+isUserHasStrongPassword);
		System.err.println("UserName : "+userName);
		
	}

}
