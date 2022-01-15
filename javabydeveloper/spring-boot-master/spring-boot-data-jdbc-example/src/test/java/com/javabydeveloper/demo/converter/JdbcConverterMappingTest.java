package com.javabydeveloper.demo.converter;

import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import com.javabydeveloper.base.BaseTest;
import com.javabydeveloper.model.ContactAddress;
import com.javabydeveloper.util.UserType;

@SpringBootTest
//@Import(JdbcCustomConverter.class)
public class JdbcConverterMappingTest extends BaseTest{

	@Autowired
	private UserJdbcConverterTestRepository userTestRepository;

	@Test
	@DisplayName("Custom-Converter-Test")
	@Sql(scripts = "/embeded_basic_mapping.sql")
	void embeddedMappingTest() {
		
		ContactAddress address = new ContactAddress();
		address.setStreetAddress("Clock Tower, Mahbub College Campus");
		address.setCity("Secunderabad");
		address.setState("Andhrapradesh");
		address.setAreaCode("500003");

		User user = new User();
		
		user.setUserName("Mike");
		user.setId(null);
		user.setPassword("password");
		user.setCreatedTime(new Date());
		user.setUserType(UserType.EMPLOYEE);
		user.setAddress(address);

		User createdUser = userTestRepository.save(user);
		System.err.println(createdUser);
		Assert.assertTrue(createdUser != null);
	}
}
