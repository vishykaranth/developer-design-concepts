package com.javabydeveloper.demo.embedded;

import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.javabydeveloper.base.BaseTest;
import com.javabydeveloper.model.College;
import com.javabydeveloper.model.ContactAddress;
import com.javabydeveloper.repository.CollegeRepository;
import com.javabydeveloper.util.UserType;

@SpringBootTest
public class EmbeddedMappingTest extends BaseTest{

	@Autowired
	private CollegeRepository collegeRepository;

	@Autowired
	private UserTestRepository userTestRepository;

	@Test
	@DisplayName("Basic Embedded Mapping")
	@Sql(scripts = "/embeded_basic_mapping.sql")
	void embeddedMappingTest() {
		College college = new College();
		college.setCollegeName("Vivekananda PG College");
		// Setting Composite Value Type
		ContactAddress address = new ContactAddress();
		address.setStreetAddress("Clock Tower, Mahbub College Campus");
		address.setCity("Secunderabad");
		address.setState("Andhrapradesh");
		address.setAreaCode("500003");
		college.setAddress(address);

		College createdCollege = collegeRepository.save(college);
		System.err.println(createdCollege);
		Assert.assertTrue(createdCollege != null);

		User user = new User();
		user.setUserName("mike");
		user.setPassword("password");
		user.setCreatedTime(new Date());
		user.setUserType(UserType.EMPLOYEE);
		user.setAddress(address);

		User createdUser = userTestRepository.save(user);
		System.err.println(createdUser);
		Assert.assertTrue(createdUser != null);
	}
}
