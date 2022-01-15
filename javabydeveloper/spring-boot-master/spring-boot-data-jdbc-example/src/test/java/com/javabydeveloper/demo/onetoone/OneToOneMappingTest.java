package com.javabydeveloper.demo.onetoone;

import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.javabydeveloper.base.BaseTest;
import com.javabydeveloper.util.UserType;

@SpringBootTest
public class OneToOneMappingTest extends BaseTest{

	@Autowired
	private UserOneToOneTestRepository userTestRepository;

	@Test
	@DisplayName("One-to-One Mapping Test")
	@Sql(scripts = "/one-to-one-mapping.sql")
	void embeddedMappingTest() {

		Credentials credentials = new Credentials();
		credentials.setUserName("peterm");
		credentials.setPassword("password");

		User user = new User();
		user.setCreatedTime(new Date());
		user.setDateofBirth(new Date());
		user.setUserType(UserType.EMPLOYEE);
		user.setCredentials(credentials);

		User createdUser = userTestRepository.save(user);
		System.err.println(createdUser);
		Assert.assertTrue(createdUser != null);
	}

}
