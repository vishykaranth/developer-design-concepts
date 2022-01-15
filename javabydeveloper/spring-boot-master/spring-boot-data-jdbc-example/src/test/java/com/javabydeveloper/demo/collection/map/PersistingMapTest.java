package com.javabydeveloper.demo.collection.map;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.javabydeveloper.base.BaseTest;
import com.javabydeveloper.util.ContactType;
import com.javabydeveloper.util.UserType;

@SpringBootTest
public class PersistingMapTest extends BaseTest{

	@Autowired
	private UserCollectionMapTestRepository userTestRepository;

	@Test
	@DisplayName("Persisting Map Test")
	@Sql(scripts = "/persist_map.sql")
	void embeddedMappingTest() {

		User user = new User();
		user.setUserName("mike");
		user.setPassword("password");
		user.setCreatedTime(new Date());
		user.setUserType(UserType.EMPLOYEE);

		Map<ContactType, Contact> contactsMap = new HashMap();
		contactsMap.put(ContactType.OFFICE, new Contact("(817) 569-8221"));
		contactsMap.put(ContactType.MOBILE, new Contact("(817) 569-8281"));
		user.setContacts(contactsMap);

		User createdUser = userTestRepository.save(user);
		System.err.println(createdUser);
		Assert.assertTrue(createdUser != null);
	}
}
